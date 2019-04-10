package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.MainActivityContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(val view: MainActivityContract.View, val model: MainActivityContract.Model) : MainActivityContract.Presenter {
    override fun init() {
        view.init()
    }

    override fun requestGetCharacters() {
        view.cleanRecycler()
        view.showLoading()
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

    override fun localGetCharacters() {
        view.cleanRecycler()
        view.showLoading()
        model.getCharacterLoadUseCase().let { characters ->
            if (characters.isEmpty()) {
                view.showToastNoItemToShow()
            } else {
                view.showCharacters(characters)
            }
            view.hideLoading()
        }
    }
}
