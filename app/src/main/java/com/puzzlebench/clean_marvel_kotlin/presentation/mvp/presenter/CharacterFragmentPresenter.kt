package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.FragmentContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFragmentPresenter(val view: FragmentContract.View, val model: FragmentContract.Model, val index: Int) : FragmentContract.Presenter {

    override fun init() {
        view.init()
        requestGetCharacters()
    }

    private fun requestGetCharacters() {
        model.getCharacterDetailServiceUseCase(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ character ->
                    view.showCharacters(character)
                    view.showLoading()
                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
    }
}
