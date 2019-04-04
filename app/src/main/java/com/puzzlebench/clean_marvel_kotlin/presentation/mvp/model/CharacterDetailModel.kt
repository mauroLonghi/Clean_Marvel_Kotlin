package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contract.FragmentContract

open class CharacterDetailModel(private val getCharacterByIdServiceUseCase: GetCharacterByIdUseCase) : FragmentContract.Model {
    override fun getCharacterDetailServiceUseCase(id: Int) = getCharacterByIdServiceUseCase.invoke(id)
}
