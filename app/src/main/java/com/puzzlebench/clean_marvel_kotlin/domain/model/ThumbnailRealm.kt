package com.puzzlebench.clean_marvel_kotlin.domain.model

import io.realm.RealmObject

open class ThumbnailRealm(
        var path: String? = DEFAULT_VALUE,
        var extension: String? = DEFAULT_VALUE
) : RealmObject()
