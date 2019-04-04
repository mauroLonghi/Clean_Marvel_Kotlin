package com.puzzlebench.clean_marvel_kotlin.domain.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_ID
import com.puzzlebench.clean_marvel_kotlin.domain.model.Object.DEFAULT_VALUE
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterRealm(

        @PrimaryKey var id: Int = DEFAULT_ID,
        var name: String? = DEFAULT_VALUE,
        var description: String? = DEFAULT_VALUE,
        var thumbnail: ThumbnailRealm? = ThumbnailRealm()
) : RealmObject()
