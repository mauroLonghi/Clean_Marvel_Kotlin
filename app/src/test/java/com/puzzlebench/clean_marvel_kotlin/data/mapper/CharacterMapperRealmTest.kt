package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.ThumbnailRealm
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class CharacterMapperRealmTest {
    companion object {
        private lateinit var mapper: CharacterRealmMapper
        private const val ID = 1011334
        private const val NAME = "sport"
        private const val DESCRIPTION = "some description"
        private const val PATH = "http:/some.com/"
        private const val EXTENSION = ".PNG"
    }

    val mockCharacterRealm = mock(CharacterRealm::class.java)
    val mockThumbnailRealm = mock(ThumbnailRealm::class.java)
    val mockCharacter = mock(Character::class.java)
    val mockThumbnail = mock(Thumbnail::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = CharacterRealmMapper()
    }

    @Test
    fun transform() {
        `when`(mockCharacterRealm.id).thenReturn(ID)
        `when`(mockCharacterRealm.name).thenReturn(NAME)
        `when`(mockCharacterRealm.description).thenReturn(DESCRIPTION)
        `when`(mockThumbnailRealm.path).thenReturn(PATH)
        `when`(mockThumbnailRealm.extension).thenReturn(EXTENSION)
        `when`(mockCharacterRealm.thumbnail).thenReturn(mockThumbnailRealm)
        assertBufferDataEquality(mapper.transform(mockCharacterRealm))
    }

    @Test
    fun transformToResponse() {
        `when`(mockCharacter.id).thenReturn(ID)
        `when`(mockCharacter.name).thenReturn(NAME)
        `when`(mockCharacter.description).thenReturn(DESCRIPTION)
        `when`(mockThumbnail.path).thenReturn(PATH)
        `when`(mockThumbnail.extension).thenReturn(EXTENSION)
        `when`(mockCharacter.thumbnail).thenReturn(mockThumbnail)
        assertBufferRealmDataEquality(mapper.transformToResponse(mockCharacter))
    }

    private fun assertBufferDataEquality(character: Character) {
        Assert.assertEquals(ID, character.id)
        Assert.assertEquals(NAME, character.name)
        Assert.assertEquals(DESCRIPTION, character.description)
        Assert.assertEquals(PATH, character.thumbnail.path)
        Assert.assertEquals(EXTENSION, character.thumbnail.extension)
    }

    private fun assertBufferRealmDataEquality(character: CharacterRealm) {
        Assert.assertEquals(ID, character.id)
        Assert.assertEquals(NAME, character.name)
        Assert.assertEquals(DESCRIPTION, character.description)
        Assert.assertEquals(PATH, character.thumbnail?.path)
        Assert.assertEquals(EXTENSION, character.thumbnail?.extension)
    }
}
