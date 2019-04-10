package com.puzzlebench.clean_marvel_kotlin.mocks.factory

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.ZERO
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail

class CharactersFactory {

    companion object Factory {

        private const val BASE_ID = ZERO
        private const val BASE_NAME = "Name"
        private const val BASE_DESCRIPTION = "Description"
        private const val BASE_PATH = "image"
        private const val BASE_EXTENSION = ".png"
        private const val MIN_LIST_CHARACTER = 1
        private const val MAX_LIST_CHARACTER = 5

        open fun getMockListCharacter(): List<Character> {
            return listOf(MIN_LIST_CHARACTER..MAX_LIST_CHARACTER).map {
                Character(BASE_ID, "$BASE_NAME$it", "$BASE_DESCRIPTION$it", Thumbnail("$BASE_PATH$it", BASE_EXTENSION))
            }
        }

        fun getMockCharacter(): Character {
            return Character(BASE_ID, BASE_NAME, BASE_DESCRIPTION, Thumbnail(BASE_PATH, BASE_EXTENSION))
        }
    }
}
