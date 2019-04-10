package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_ID_TEST
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetCharacterDetailUseCaseTest {

    private lateinit var characterService: CharacterService
    @Mock
    lateinit var itemsMock: Character

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val observable = Observable.just(itemsMock)
        characterService = Mockito.mock(CharacterService::class.java)
        `when`(characterService.getCaracters(DEFAULT_ID_TEST)).thenReturn(observable)
    }

    @Test
    operator fun invoke() {
        val getCharacterByIdUseCase = GetCharacterByIdUseCase(characterService)
        getCharacterByIdUseCase.invoke(DEFAULT_ID_TEST)
        verify(characterService).getCaracters(DEFAULT_ID_TEST)
    }
}
