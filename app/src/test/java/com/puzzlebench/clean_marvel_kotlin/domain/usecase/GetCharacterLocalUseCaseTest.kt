package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetCharacterLocalUseCaseTest {

    private lateinit var characterService: CharacterRepository.LoadRepository

    @Mock
    lateinit var itemsMock: List<Character>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        characterService = Mockito.mock(CharacterRepository.LoadRepository::class.java)
        `when`(characterService.loadCharacters()).thenReturn(itemsMock)
    }

    @Test
    operator fun invoke() {
        val getCharacterLoadUseCase = GetCharacterLoadUseCase(characterService)
        getCharacterLoadUseCase.invoke()
        verify(characterService).loadCharacters()
    }
}
