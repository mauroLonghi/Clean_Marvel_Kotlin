package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_response.*
import java.lang.ref.WeakReference

class CharecterFragmentView(fragment: CharacterFragment) {
    private val fragmentRef = WeakReference(fragment)

    companion object{
        private const val POINT_URL = "."
    }

    fun init() {
        val fragment = fragmentRef.get()
        fragment ?: showLoading()
    }

    fun hideLoading() {
        fragmentRef.get()?.activity?.progressBar?.visibility = View.GONE
    }

    fun showToastNoItemToShow() {
        val fragment = fragmentRef.get()
        fragment?.let {
            val message = fragment.getString(R.string.message_no_items_to_show)
            fragment.activity.applicationContext.showToast(message)
        }
    }

    fun showCharacters(characters: Character) {
        val activity = fragmentRef.get()

        activity?.character_name?.text =
                if (!characters.name.isEmpty()) characters.name
                else activity?.getString(R.string.message_no_items_to_show)

        activity?.character_description?.text =
                if (!characters.description.isEmpty()) characters.description
                else activity?.getString(R.string.message_no_items_to_show)

        activity?.character_image?.getImageByUrl(
                "${characters.thumbnail.path}${POINT_URL}${characters.thumbnail.extension}"
        )
    }

    fun showLoading() {
        fragmentRef.get()?.activity?.progressBar?.visibility = View.VISIBLE
    }
}
