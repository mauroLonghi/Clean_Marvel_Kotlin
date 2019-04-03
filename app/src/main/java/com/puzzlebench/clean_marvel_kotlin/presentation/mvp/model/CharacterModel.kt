package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase

open class CharacterModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase
                          , private val storeCharacterUseCase: GetCharacterStoreUseCase
) {
    open fun getCharacterDataServiceUseCase() = getCharacterServiceUseCase.invoke()
    open fun getCharacterStoreServiceUseCase(characters: List<Character>) = storeCharacterUseCase.invoke(characters)
}
