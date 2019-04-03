package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.StoreCharacterServiceImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

open class GetCharacterStoreUseCase(private val characterServiceImp: StoreCharacterServiceImpl) {

    open operator fun invoke(characters: List<Character>) = characterServiceImp.saveCharacters(characters)
}
