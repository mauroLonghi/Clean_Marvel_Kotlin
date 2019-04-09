package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.ThumbnailRealm
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class CharacterMapperRealmTest {
    companion object {
        private lateinit var mapper: CharacterRealmMapper
        private const val ID = 1011334
        private const val NAME = "sport"
        private const val DESCRIPTION = "some description"
        private const val PATH = "http:/some.com/"
        private const val EXTENSION = ".PNG"
    }

    @Before
    fun setUp() {
        mapper = CharacterRealmMapper()
    }

    @Test
    fun transform() {
        val mockThumbnailRealm = ThumbnailRealm(PATH, EXTENSION)
        val mockCharacterRealm = CharacterRealm(ID, NAME, DESCRIPTION, mockThumbnailRealm)
        val result = mapper.transform(mockCharacterRealm)
        assertBufferooDataEquality(mockCharacterRealm, result)
    }

    @Test
    fun transformToResponse() {
        val mockThumbnail = Thumbnail(PATH, EXTENSION)
        val mockCharacter = Character(ID, NAME, DESCRIPTION, mockThumbnail)
        val result = mapper.transformToResponse(mockCharacter)
        assertBufferooDataEquality(result, mockCharacter)
    }

    private fun assertBufferooDataEquality(characterRealm: CharacterRealm,
                                           character: Character) {
        Assert.assertEquals(characterRealm.id, character.id)
        Assert.assertEquals(characterRealm.name, character.name)
        Assert.assertEquals(characterRealm.description, character.description)
        Assert.assertEquals(characterRealm.thumbnail?.path, character.thumbnail.path)
        Assert.assertEquals(characterRealm.thumbnail?.extension, character.thumbnail.extension)
    }

}
