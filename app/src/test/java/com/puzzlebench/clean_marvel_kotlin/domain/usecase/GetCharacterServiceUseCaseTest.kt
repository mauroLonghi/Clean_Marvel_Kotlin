package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetCharacterServiceUseCaseTest {

    private lateinit var characterService: CharacterService

    @Before
    fun setUp() {
        val videoItems = CharactersFactory.getMockListCharacter()
        val observable = Observable.just(videoItems)
        characterService = mock(CharacterService::class.java)
        `when`(characterService.getCaracters()).thenReturn(observable)
    }

    @Test
    operator fun invoke() {
        val getCharacterServiceUseCase = GetCharacterServiceUseCase(characterService)
        getCharacterServiceUseCase.invoke()
        verify(characterService).getCaracters()
    }
}
