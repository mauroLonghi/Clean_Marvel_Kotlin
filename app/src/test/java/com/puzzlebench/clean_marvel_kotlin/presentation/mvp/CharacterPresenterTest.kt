package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.LoadCharacterLocalImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.StoreCharacterServiceImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterLoadUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterView
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class CharacterPresenterTest {

    private var view = mock(CharacterView::class.java)
    private var model = mock(CharacterModel::class.java)
    private var characterServiceImp = mock(CharacterServicesImpl::class.java)

    private var characterLoadImp = mock(LoadCharacterLocalImpl::class.java)
    private var characterStoreImp = mock(StoreCharacterServiceImpl::class.java)

    private lateinit var characterPresenter: CharacterPresenter
    private lateinit var characterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var characterLoadUseCase: GetCharacterLoadUseCase
    private lateinit var characterStoreUseCase: GetCharacterStoreUseCase

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        characterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        characterLoadUseCase = GetCharacterLoadUseCase(characterLoadImp)
        characterStoreUseCase = GetCharacterStoreUseCase(characterStoreImp)
        characterPresenter = CharacterPresenter(view, model)

    }

    @Ignore
    fun reposeWithError() {
        Mockito.`when`(characterServiceUseCase.invoke()).thenReturn(Observable.error(Exception("")))
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).hideLoading()
        verify(view).showToastNetworkError("")
    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharecters = CharactersFactory.getMockCharacter()
        val observable = Observable.just(itemsCharecters)
        Mockito.`when`(characterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).hideLoading()
        verify(view).showCharacters(itemsCharecters)

    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharacters = emptyList<Character>()
        val observable = Observable.just(itemsCharacters)
        Mockito.`when`(characterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCaracters()

    }

}
