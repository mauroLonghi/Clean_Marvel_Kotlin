package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetCharacterServiceUseCaseTest {

    private lateinit var characterService: CharacterService

    @Mock
    lateinit var itemsMock: List<Character>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val observable = Observable.just(itemsMock)
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
