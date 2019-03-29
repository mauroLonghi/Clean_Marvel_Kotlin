package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFragmentPresenter(view: CharecterFragmentView, private val getChatacterServiceByIdUseCase: GetCharacterByIdUseCase, val index: Int) : Presenter<CharecterFragmentView>(view) {

    fun init() {
        view.init()
        requestGetCharacters()
    }

    private fun requestGetCharacters() {

        getChatacterServiceByIdUseCase
                .invoke(index)
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