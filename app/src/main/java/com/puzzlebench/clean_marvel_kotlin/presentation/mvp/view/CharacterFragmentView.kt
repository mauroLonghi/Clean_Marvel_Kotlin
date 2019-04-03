package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_response.*
import java.lang.ref.WeakReference

class CharacterFragmentView(fragment: CharacterFragment) {
    private val fragmentRef = WeakReference(fragment)

    companion object {
        private const val DOT_URL = "."
    }

    fun init() {
        getFragment() ?: showLoading()
    }

    private fun getFragment() = fragmentRef.get()

    fun hideLoading() {
        getFragment()?.activity?.progressBar?.visibility = View.GONE
    }

    fun showToastNoItemToShow() {
        getFragment()?.let {
            val message = it.getString(R.string.message_no_items_to_show)
            getFragment()?.activity?.applicationContext?.showToast(message)
        }
    }

    fun showCharacters(characters: Character) {
        getFragment()?.character_name?.text =
                if (!characters.name.isEmpty()) characters.name
                else getFragment()?.getString(R.string.message_no_items_to_show)

        getFragment()?.character_description?.text =
                if (!characters.description.isEmpty()) characters.description
                else getFragment()?.getString(R.string.message_no_items_to_show)

        getFragment()?.character_image?.getImageByUrl(
                "${characters.thumbnail.path}$DOT_URL${characters.thumbnail.extension}"
        )
    }

    fun showLoading() {
        getFragment()?.activity?.progressBar?.visibility = View.VISIBLE
    }
}
