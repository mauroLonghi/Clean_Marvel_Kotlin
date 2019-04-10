package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetCharacterStoreUseCaseTest {

    private lateinit var characterService: CharacterRepository.StoreRepository

    @Mock
    lateinit var itemsMock: List<Character>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        characterService = mock(CharacterRepository.StoreRepository::class.java)
        `when`(characterService.saveCharacters(itemsMock)).then {}
    }

    @Test
    operator fun invoke() {
        val getCharacterStoreUseCase = GetCharacterStoreUseCase(characterService)
        getCharacterStoreUseCase.invoke(itemsMock)
        verify(characterService).saveCharacters(itemsMock)
    }
}
