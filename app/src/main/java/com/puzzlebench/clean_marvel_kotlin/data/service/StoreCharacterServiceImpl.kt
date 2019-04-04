package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterRealmMapper
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.realm.Realm
import io.realm.kotlin.where

class StoreCharacterServiceImpl(private val mapper: CharacterRealmMapper = CharacterRealmMapper()) {

    fun saveCharacters(users: List<Character>) {
        var realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.deleteAll()
            var userRealm: List<CharacterRealm> = mapper.transform(users)
            realm.insert(userRealm)
        }
    }
}
