package com.puzzlebench.clean_marvel_kotlin.presentation

import android.app.Application
import io.realm.Realm

class BaseDbRealm() : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }

}
