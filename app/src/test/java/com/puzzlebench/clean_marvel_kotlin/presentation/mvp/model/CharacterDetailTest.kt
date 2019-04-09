package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_ID_TEST
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.FragmentContract
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterDetailTest {
    private lateinit var model: FragmentContract.Model
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    private var characterService = Mockito.mock(CharacterService::class.java)
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        getCharacterByIdUseCase = GetCharacterByIdUseCase(characterService)

        model = CharacterDetailModel(getCharacterByIdUseCase)

    }

    @Test
    fun checkCharacterDetailServiceUseCase(){
        val itemsCharacters = CharactersFactory.getMockCharacter()
        val observable = Observable.just(itemsCharacters)
        Mockito.`when`(model.getCharacterDetailServiceUseCase(DEFAULT_ID_TEST)).thenReturn(observable)
    }
}
