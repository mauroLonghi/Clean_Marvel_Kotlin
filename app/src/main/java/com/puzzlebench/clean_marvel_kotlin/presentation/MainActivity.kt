package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterRealmMapper
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.StoreCharacterServiceImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView
import io.realm.Realm

open class MainActivity : BaseRxActivity() {
    private lateinit var realm: Realm
    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val storeCharacterUseCase = GetCharacterStoreUseCase(StoreCharacterServiceImpl())
    val presenter = CharacterPresenter(CharecterView(this), getCharacterServiceUseCase,storeCharacterUseCase, subscriptions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.puzzlebench.clean_marvel_kotlin.R.layout.activity_main)
        Realm.init(this)
        presenter.init()
    }
    override fun onDestroy() {
        super.onDestroy()

        realm.close()
    }
}
