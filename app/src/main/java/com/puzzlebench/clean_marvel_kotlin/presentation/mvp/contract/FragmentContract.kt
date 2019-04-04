package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface FragmentContract {

    interface Model {
        fun getCharacterDetailServiceUseCase(id: Int): Observable<Character>
    }

    interface Presenter {
        fun init()
    }

    interface View {
        fun init()
        fun showToastNoItemToShow()
        fun hideLoading()
        fun showCharacters(characters: Character)
        fun showLoading()
    }
}
