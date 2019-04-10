package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_VALUE
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.NO_DELAY
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterLoadUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class CharacterPresenterTest {

    private lateinit var model: MainActivityContract.Model
    private lateinit var characterPresenter: MainActivityContract.Presenter
    private var view = mock(CharacterView::class.java)
    private var getCharacterServiceUseCase = mock(GetCharacterServiceUseCase::class.java)
    private var getCharacterStoreUseCase = mock(GetCharacterStoreUseCase::class.java)
    private var getCharacterLoadUseCase = mock(GetCharacterLoadUseCase::class.java)

    @Mock
    lateinit var characters: List<Character>

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
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        model = CharacterModel(getCharacterServiceUseCase, getCharacterStoreUseCase, getCharacterLoadUseCase)
        characterPresenter = CharacterPresenter(view, model)
    }

    @Test
    fun reposeWithError() {
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(Observable.error(Exception(DEFAULT_VALUE)))
        characterPresenter.requestGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(getCharacterServiceUseCase).invoke()
        verify(view).hideLoading()
        verify(view).showToastNetworkError(DEFAULT_VALUE)
    }

    @Test
    fun reposeWithItemToShow() {
        val observable = Observable.just(characters)
        `when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.requestGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(getCharacterServiceUseCase).invoke()
        verify(view).showCharacters(characters)
        verify(view).hideLoading()
    }

    @Test
    fun reposeWithoutItemToShow() {
        `when`(characters.isEmpty()).thenReturn(true)
        val observable = Observable.just(characters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.requestGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(getCharacterServiceUseCase).invoke()
        verify(view).showToastNoItemToShow()
        verify(view).hideLoading()
    }

    @Test
    fun responseCharacterLocal() {
        `when`(getCharacterLoadUseCase.invoke()).thenReturn(characters)
        characterPresenter.localGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(getCharacterLoadUseCase).invoke()
        verify(view).showCharacters(characters)
        verify(view).hideLoading()
    }

    @Test
    fun responseEmptyCharacterLocal() {
        `when`(characters.isEmpty()).thenReturn(true)
        `when`(getCharacterLoadUseCase.invoke()).thenReturn(characters)
        characterPresenter.localGetCharacters()
        verify(view).cleanRecycler()
        verify(view).showLoading()
        verify(getCharacterLoadUseCase).invoke()
        verify(view).showToastNoItemToShow()
        verify(view).hideLoading()
    }
}
