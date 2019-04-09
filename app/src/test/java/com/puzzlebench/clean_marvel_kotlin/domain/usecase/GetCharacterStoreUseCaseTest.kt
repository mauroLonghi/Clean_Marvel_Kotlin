package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class GetCharacterStoreUseCaseTest {

    private lateinit var characterService: CharacterRepository.StoreRepository

    @Before
    fun setUp() {
        val itemsMock = CharactersFactory.getMockListCharacter()
        val observable = Observable.just(itemsMock)
        characterService = Mockito.mock(CharacterRepository.StoreRepository::class.java)
        Mockito.`when`(characterService.saveCharacters(itemsMock)).then {}
    }

    @Test
    operator fun invoke() {
        val itemsMock = CharactersFactory.getMockListCharacter()
        val getCharacterStoreUseCase = GetCharacterStoreUseCase(characterService)
        getCharacterStoreUseCase.invoke(itemsMock)
        verify(characterService).saveCharacters(itemsMock)
    }
}
