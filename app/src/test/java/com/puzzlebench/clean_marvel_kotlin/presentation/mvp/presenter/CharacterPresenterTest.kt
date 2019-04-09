package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_VALUE
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.NO_DELAY
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterLoadUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.MainActivityContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class CharacterPresenterTest {

    private var view = mock(CharacterView::class.java)
    private lateinit var model: MainActivityContract.Model
    private lateinit var characterPresenter: MainActivityContract.Presenter

    private var characterService = mock(CharacterService::class.java)
    private var characterLoad = mock(CharacterRepository.LoadRepository::class.java)
    private var characterStore = mock(CharacterRepository.StoreRepository::class.java)

    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var getCharacterStoreUseCase: GetCharacterStoreUseCase
    private lateinit var getCharacterLoadUseCase: GetCharacterLoadUseCase

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            val immediate = object : Scheduler() {

                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, NO_DELAY.toLong(), unit)
                }

                override fun createWorker(): Scheduler.Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        getCharacterServiceUseCase = GetCharacterServiceUseCase(characterService)
        getCharacterStoreUseCase = GetCharacterStoreUseCase(characterStore)
        getCharacterLoadUseCase = GetCharacterLoadUseCase(characterLoad)

        model = CharacterModel(getCharacterServiceUseCase, getCharacterStoreUseCase, getCharacterLoadUseCase)
        characterPresenter = CharacterPresenter(view, model)
    }

    @Test
    fun reposeWithError() {
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(Observable.error(Exception(DEFAULT_VALUE)))
        characterPresenter.requestGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showToastNetworkError(DEFAULT_VALUE)
    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharacters = CharactersFactory.getMockListCharacter()
        val observable = Observable.just(itemsCharacters)
        Mockito.`when`(model.getCharacterDataServiceUseCase()).thenReturn(observable)
        characterPresenter.requestGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(view).showCharacters(itemsCharacters)
        verify(view).hideLoading()
    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharacters = emptyList<Character>()
        val observable = Observable.just(itemsCharacters)
        Mockito.`when`(model.getCharacterDataServiceUseCase()).thenReturn(observable)
        characterPresenter.requestGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(view).showToastNoItemToShow()
        verify(view).hideLoading()
    }

    @Test
    fun responseCharacterLocal() {
        val itemsCharacters = CharactersFactory.getMockListCharacter()
        Mockito.`when`(model.getCharacterLoadUseCase()).thenReturn(itemsCharacters)
        characterPresenter.localGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(view).showCharacters(itemsCharacters)
        verify(view).hideLoading()
    }

    @Test
    fun responseEmptyCharacterLocal() {
        val itemsCharacters = emptyList<Character>()
        Mockito.`when`(model.getCharacterLoadUseCase()).thenReturn(itemsCharacters)
        characterPresenter.localGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(view).showToastNoItemToShow()
        verify(view).hideLoading()
    }
}
