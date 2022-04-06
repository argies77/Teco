package com.telecom.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context)
            .load(it)
            .into(this)
    }
}
