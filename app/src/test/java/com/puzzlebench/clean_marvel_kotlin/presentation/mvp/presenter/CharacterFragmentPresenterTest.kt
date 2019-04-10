package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_ID_TEST
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_VALUE
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.NO_DELAY
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.FragmentContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterDetailModel
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

class CharacterFragmentPresenterTest {
    private var view = Mockito.mock(FragmentContract.View::class.java)
    private lateinit var model: FragmentContract.Model
    private lateinit var characterPresenter: FragmentContract.Presenter
    private var getCharacterServiceByIdUseCase = mock(GetCharacterByIdUseCase::class.java)

    @Mock
    lateinit var character: Character

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
        model = CharacterDetailModel(getCharacterServiceByIdUseCase)
        characterPresenter = CharacterFragmentPresenter(view, model, DEFAULT_ID_TEST)
    }

    @Test
    fun reposeWithItemToShow() {
        val observable = Observable.just(character)
        `when`(getCharacterServiceByIdUseCase.invoke(DEFAULT_ID_TEST)).thenReturn(observable)
        characterPresenter.init()
        verify(getCharacterServiceByIdUseCase).invoke(DEFAULT_ID_TEST)
        verify(view).showCharacters(character)
        verify(view).showLoading()
    }

    @Test
    fun reposeWithoutItemToShow() {
        `when`(getCharacterServiceByIdUseCase.invoke(DEFAULT_ID_TEST)).thenReturn(Observable.error(Exception(DEFAULT_VALUE)))
        characterPresenter.init()
        verify(getCharacterServiceByIdUseCase).invoke(DEFAULT_ID_TEST)
        verify(view).hideLoading()
        verify(view).showToastNetworkError(DEFAULT_VALUE)
    }

    @Test
    fun reposeWithError() {
        `when`(getCharacterServiceByIdUseCase.invoke(DEFAULT_ID_TEST)).thenReturn(Observable.error(Exception(DEFAULT_VALUE)))
        characterPresenter.init()
        verify(getCharacterServiceByIdUseCase).invoke(DEFAULT_ID_TEST)
        verify(view).hideLoading()
        verify(view).showToastNetworkError(DEFAULT_VALUE)
    }
}
