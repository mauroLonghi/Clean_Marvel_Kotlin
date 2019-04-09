package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class GetCharacterLocalUseCaseTest {

    private lateinit var characterService: CharacterRepository.LoadRepository

    @Before
    fun setUp() {
        val videoItems = CharactersFactory.getMockListCharacter()
        characterService = Mockito.mock(CharacterRepository.LoadRepository::class.java)
        Mockito.`when`(characterService.loadCharacters()).thenReturn(videoItems)
    }

    @Test
    operator fun invoke() {
        val getCharacterLoadUseCase = GetCharacterLoadUseCase(characterService)
        getCharacterLoadUseCase.invoke()
        verify(characterService).loadCharacters()
    }
}
