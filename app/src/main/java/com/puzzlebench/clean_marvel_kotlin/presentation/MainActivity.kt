package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.StoreCharacterServiceImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterStoreUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseRxActivity() {

    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val storeCharacterUseCase = GetCharacterStoreUseCase(StoreCharacterServiceImpl())

    val presenter = CharacterPresenter(CharacterView(this), CharacterModel(getCharacterServiceUseCase, storeCharacterUseCase))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.puzzlebench.clean_marvel_kotlin.R.layout.activity_main)
        presenter.init()

        button_db.setOnClickListener {
            presenter.requestGetCharacters()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.close()
        }
    }
}
