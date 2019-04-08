package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterLoadUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.MainActivityContract

open class CharacterModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase
                          , private val storeCharacterUseCase: GetCharacterStoreUseCase
                          , private val loadCharacterUseCase: GetCharacterLoadUseCase
) : MainActivityContract.Model {
    override fun getCharacterDataServiceUseCase() = getCharacterServiceUseCase.invoke()
    override fun getCharacterStoreServiceUseCase(characters: List<Character>) = storeCharacterUseCase.invoke(characters)
    override fun getCharacterLoadUseCase() = loadCharacterUseCase.invoke()
}
