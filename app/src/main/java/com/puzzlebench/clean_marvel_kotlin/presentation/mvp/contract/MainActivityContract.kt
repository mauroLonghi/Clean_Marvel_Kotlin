package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface MainActivityContract {

    interface Model {
        fun getCharacterDataServiceUseCase(): Observable<List<Character>>
        fun getCharacterStoreServiceUseCase(character: List<Character>)
        fun getCharacterLoadUseCase(): List<Character>
    }

    interface Presenter {
        fun init()
        fun requestGetCharacters()
        fun localGetCharacters()
    }

    interface View {
        fun init()
        fun showToastNoItemToShow()
        fun showToastNetworkError(error: String)
        fun hideLoading()
        fun showCharacters(characters: List<Character>)
        fun cleanRecycler()
        fun showLoading()
    }
}
