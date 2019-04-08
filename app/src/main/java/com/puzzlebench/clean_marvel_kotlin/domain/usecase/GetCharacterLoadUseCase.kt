package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.LoadCharacterLocalImpl

class GetCharacterLoadUseCase(private val loadCharactersImpl: LoadCharacterLocalImpl) {

    open operator fun invoke() = loadCharactersImpl.loadCharacters()
}
