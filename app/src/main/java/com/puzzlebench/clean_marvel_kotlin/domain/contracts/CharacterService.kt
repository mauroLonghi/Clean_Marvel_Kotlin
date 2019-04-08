package com.puzzlebench.clean_marvel_kotlin.domain.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface CharacterService {
    fun getCaracters(): Observable<List<Character>>
    fun getCaracters(id: Int): Observable<Character>
}
