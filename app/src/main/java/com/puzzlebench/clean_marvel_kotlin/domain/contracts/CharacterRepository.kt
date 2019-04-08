package com.puzzlebench.clean_marvel_kotlin.domain.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

interface CharacterRepository {
    interface StoreRepository {
        fun saveCharacters(users: List<Character>)
    }

    interface LoadRepository {
        fun loadCharacters(): List<Character>
    }
}
