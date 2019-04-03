package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdUseCase

open class CharacterDetailModel(private val getCharacterByIdServiceUseCase: GetCharacterByIdUseCase) {
    fun getCharacterDetailServiceUseCase(id: Int) = getCharacterByIdServiceUseCase.invoke(id)
}
