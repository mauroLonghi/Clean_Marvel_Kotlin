package com.puzzlebench.clean_marvel_kotlin.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.getImageByUrl(url: String) {
    Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
}
