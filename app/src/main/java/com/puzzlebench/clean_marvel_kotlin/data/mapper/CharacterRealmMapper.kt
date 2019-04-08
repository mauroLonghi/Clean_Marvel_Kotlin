package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.ThumbnailRealm

class CharacterRealmMapper : BaseMapperRepository<CharacterRealm, Character> {

    override fun transform(characterReal: CharacterRealm): Character = Character(
            characterReal.id,
            characterReal.name.toString(),
            characterReal.description.toString(),
            transformToThumbnailReal(characterReal.thumbnail)
    )

    override fun transformToResponse(type: Character): CharacterRealm = CharacterRealm(
            type.id,
            type.name,
            type.description,
            transformToThumbnail(type.thumbnail)
    )

    fun transformToThumbnail(thumbnailResponse: Thumbnail): ThumbnailRealm = ThumbnailRealm(
            thumbnailResponse.path,
            thumbnailResponse.extension
    )

    fun transformToThumbnailReal(thumbnail: ThumbnailRealm?) = Thumbnail(
            thumbnail?.path.toString(),
            thumbnail?.extension.toString()
    )

    fun transform(charactersResponse: List<Character>) = charactersResponse.map { transformToResponse(it) }
    fun transformToListCharacters(charactersResponse: List<CharacterRealm>) = charactersResponse.map { transform(it) }


}
