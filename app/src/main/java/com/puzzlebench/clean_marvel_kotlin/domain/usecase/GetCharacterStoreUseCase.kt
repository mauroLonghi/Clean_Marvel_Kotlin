package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

open class GetCharacterStoreUseCase(private val characterService: CharacterRepository.StoreRepository) {

    open operator fun invoke(characters: List<Character>) = characterService.saveCharacters(characters)
}
