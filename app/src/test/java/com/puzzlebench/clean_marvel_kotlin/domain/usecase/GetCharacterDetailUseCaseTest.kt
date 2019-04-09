package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_ID_TEST
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class GetCharacterDetailUseCaseTest {

    private lateinit var characterService: CharacterService

    @Before
    fun setUp() {
        val character = CharactersFactory.getMockCharacter()
        val observable = Observable.just(character)
        characterService = Mockito.mock(CharacterService::class.java)
        Mockito.`when`(characterService.getCaracters(DEFAULT_ID_TEST)).thenReturn(observable)
    }

    @Test
    operator fun invoke() {
        val getCharacterByIdUseCase = GetCharacterByIdUseCase(characterService)
        getCharacterByIdUseCase.invoke(DEFAULT_ID_TEST)
        verify(characterService).getCaracters(DEFAULT_ID_TEST)
    }
}
