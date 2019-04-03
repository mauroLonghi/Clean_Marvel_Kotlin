package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(view: CharecterView, private val getChatacterServiceUseCase: GetCharacterServiceUseCase, private val storeCharacterServiceUseCase: GetCharacterStoreUseCase, val subscriptions: CompositeDisposable) : Presenter<CharecterView>(view) {

    fun init() {
        view.init()
        //requestGetCharacters()
    }

    fun requestGetCharacters() {
        val subscription = getChatacterServiceUseCase
                .invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters ->
                    if (characters.isEmpty()) {
                        view.showToastNoItemToShow()
                    } else {
                        storeCharacterServiceUseCase.invoke(characters)
                        view.showCharacters(characters)
                    }
                    view.hideLoading()
                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
        subscriptions.add(subscription)
    }
}
