package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterLoadUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.MainActivityContract
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterModelTest {

    private lateinit var model: MainActivityContract.Model
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var getCharacterStoredUseCase: GetCharacterStoreUseCase
    private lateinit var getCharacterLoadUseCase: GetCharacterLoadUseCase

    private var characterService = Mockito.mock(CharacterService::class.java)
    private var characterStored = Mockito.mock(CharacterRepository.StoreRepository::class.java)
    private var characterLoad = Mockito.mock(CharacterRepository.LoadRepository::class.java)
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        getCharacterServiceUseCase = GetCharacterServiceUseCase(characterService)
        getCharacterStoredUseCase = GetCharacterStoreUseCase(characterStored)
        getCharacterLoadUseCase = GetCharacterLoadUseCase(characterLoad)

        model = CharacterModel(getCharacterServiceUseCase, getCharacterStoredUseCase, getCharacterLoadUseCase)

    }

    @Test
    fun checkDataServiceUseCase() {
        val itemsCharacters = CharactersFactory.getMockListCharacter()
        val observable = Observable.just(itemsCharacters)
        Mockito.`when`(model.getCharacterDataServiceUseCase()).thenReturn(observable)
    }

    @Test
    fun checkStoredUseCase() {
        val itemsCharacters = CharactersFactory.getMockListCharacter()
        Mockito.`when`(model.getCharacterStoreServiceUseCase(itemsCharacters)).then { }
    }

    @Test
    fun checkLoadUseCase() {
        val itemsCharacters = CharactersFactory.getMockListCharacter()
        Mockito.`when`(model.getCharacterLoadUseCase()).thenReturn(itemsCharacters)
    }
}
