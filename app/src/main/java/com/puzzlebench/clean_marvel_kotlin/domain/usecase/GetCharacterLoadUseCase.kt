package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository

class GetCharacterLoadUseCase(private val loadCharacters: CharacterRepository.LoadRepository) {

    open operator fun invoke() = loadCharacters.loadCharacters()
}
