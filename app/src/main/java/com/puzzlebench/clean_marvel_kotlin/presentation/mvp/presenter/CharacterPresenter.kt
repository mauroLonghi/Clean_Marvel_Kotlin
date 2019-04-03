package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharecterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(view: CharecterView, val model: CharacterModel) : Presenter<CharecterView>(view) {
    fun init() {
        view.init()
    }

    fun requestGetCharacters() {
        model.getCharacterDataServiceUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters ->
                    if (characters.isEmpty()) {
                        view.showToastNoItemToShow()
                    } else {
                        model.getCharacterStoreServiceUseCase(characters)
                        view.showCharacters(characters)
                    }
                    view.hideLoading()
                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
    }
}
