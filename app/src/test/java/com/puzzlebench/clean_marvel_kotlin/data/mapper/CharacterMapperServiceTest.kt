package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.service.response.CharacterResponse
import com.puzzlebench.clean_marvel_kotlin.data.service.response.ThumbnailResponse
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class CharacterMapperServiceTest {
    companion object {
        private lateinit var mapper: CharacterMapperService
        private const val ID = 1011334
        private const val NAME = "sport"
        private const val DESCRIPTION = "some description"
        private const val PATH = "http:/some.com/"
        private const val EXTENSION = ".PNG"
    }

    @Before
    fun setUp() {
        mapper = CharacterMapperService()
    }

    @Test
    fun transform() {
        val mockThumbnailResponse = ThumbnailResponse(PATH, EXTENSION)
        val mockCharacterResponse = CharacterResponse(ID, NAME, DESCRIPTION, mockThumbnailResponse)
        val result = mapper.transform(mockCharacterResponse)
        assertBufferooDataEquality(mockCharacterResponse, result)
    }

    @Test
    fun transformToResponse() {
        val mockThumbnail = Thumbnail(PATH, EXTENSION)
        val mockCharacter = Character(ID, NAME, DESCRIPTION, mockThumbnail)
        val result = mapper.transformToResponse(mockCharacter)
        assertBufferooDataEquality(result, mockCharacter)
    }

    private fun assertBufferooDataEquality(characterResponse: CharacterResponse,
                                           character: Character) {
        Assert.assertEquals(characterResponse.id, character.id)
        Assert.assertEquals(characterResponse.name, character.name)
        Assert.assertEquals(characterResponse.description, character.description)
        Assert.assertEquals(characterResponse.thumbnail.path, character.thumbnail.path)
        Assert.assertEquals(characterResponse.thumbnail.extension, character.thumbnail.extension)
    }
}
