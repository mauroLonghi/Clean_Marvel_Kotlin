package com.puzzlebench.clean_marvel_kotlin.domain

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

interface CharacterRepository {
        fun saveCharacters(users: List<Character>)
}
