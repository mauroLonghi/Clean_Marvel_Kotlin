package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterRealmMapper
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.realm.Realm

class LoadCharacterLocalImpl(private val mapper: CharacterRealmMapper = CharacterRealmMapper()) : CharacterRepository.LoadRepository {

    override fun loadCharacters(): List<Character> {
        var realm = Realm.getDefaultInstance()
        val charactersRealm = realm.where(CharacterRealm::class.java).findAll()
        return mapper.transformToListCharacters(charactersRealm)
    }
}
