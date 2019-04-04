package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.FragmentContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterDetailModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFragmentPresenter(val view: CharacterFragmentView, val model: CharacterDetailModel, val index: Int) : FragmentContract.Presenter {

    override fun init() {
        view.init()
        requestGetCharacters()
    }

    private fun requestGetCharacters() {

        model.getCharacterDetailServiceUseCase(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ character ->
                    if (character == null) {
                        view.showToastNoItemToShow()
                    } else {
                        view.showCharacters(character)
                    }
                    view.showLoading()
                    view.showCharacters(character)

                }, { e ->
                    view.hideLoading()
                    view.showToastNoItemToShow()
                })
    }
}
