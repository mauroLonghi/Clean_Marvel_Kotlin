package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

open class GetCharacterByIdUseCase(private val characterService: CharacterService) {

    open operator fun invoke(id: Int): Observable<Character> = characterService.getCaracters(id)
}
