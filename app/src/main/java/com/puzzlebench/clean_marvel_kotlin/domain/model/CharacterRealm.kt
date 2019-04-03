package com.puzzlebench.clean_marvel_kotlin.domain.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

const val DEFAULT_ID = 0
const val DEFAULT_VALUE = ""

open class CharacterRealm(
        @PrimaryKey var id: Int = DEFAULT_ID,
        var name: String? = DEFAULT_VALUE,
        var description: String? = DEFAULT_VALUE,
        var thumbnail: ThumbnailRealm? = ThumbnailRealm()
) : RealmObject()
