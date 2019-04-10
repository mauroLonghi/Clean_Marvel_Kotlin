package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.service.response.CharacterResponse
import com.puzzlebench.clean_marvel_kotlin.data.service.response.ThumbnailResponse
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class CharacterMapperServiceTest {
    companion object {
        private lateinit var mapper: CharacterMapperService
        private const val ID = 1011334
        private const val NAME = "sport"
        private const val DESCRIPTION = "some description"
        private const val PATH = "http:/some.com/"
        private const val EXTENSION = ".PNG"
    }

    val mockCharacterResponse = mock(CharacterResponse::class.java)
    val mockThumbnailResponse = mock(ThumbnailResponse::class.java)
    val mockCharacter = mock(Character::class.java)
    val mockThumbnail = mock(Thumbnail::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = CharacterMapperService()
    }

    @Test
    fun transform() {
        `when`(mockCharacterResponse.id).thenReturn(ID)
        `when`(mockCharacterResponse.name).thenReturn(NAME)
        `when`(mockCharacterResponse.description).thenReturn(DESCRIPTION)
        `when`(mockThumbnailResponse.path).thenReturn(PATH)
        `when`(mockThumbnailResponse.extension).thenReturn(EXTENSION)
        `when`(mockCharacterResponse.thumbnail).thenReturn(mockThumbnailResponse)
        assertBufferDataEquality(mapper.transform(mockCharacterResponse))
    }

    @Test
    fun transformToResponse() {
        `when`(mockCharacter.id).thenReturn(ID)
        `when`(mockCharacter.name).thenReturn(NAME)
        `when`(mockCharacter.description).thenReturn(DESCRIPTION)
        `when`(mockThumbnail.path).thenReturn(PATH)
        `when`(mockThumbnail.extension).thenReturn(EXTENSION)
        `when`(mockCharacter.thumbnail).thenReturn(mockThumbnail)
        assertBufferDataResponseEquality(mapper.transformToResponse(mockCharacter))
    }

    private fun assertBufferDataEquality(character: Character) {
        Assert.assertEquals(ID, character.id)
        Assert.assertEquals(NAME, character.name)
        Assert.assertEquals(DESCRIPTION, character.description)
        Assert.assertEquals(PATH, character.thumbnail.path)
        Assert.assertEquals(EXTENSION, character.thumbnail.extension)
    }

    private fun assertBufferDataResponseEquality(character: CharacterResponse) {
        Assert.assertEquals(ID, character.id)
        Assert.assertEquals(NAME, character.name)
        Assert.assertEquals(DESCRIPTION, character.description)
        Assert.assertEquals(PATH, character.thumbnail.path)
        Assert.assertEquals(EXTENSION, character.thumbnail.extension)
    }
}
