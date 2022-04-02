package com.openBank.util

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFresco(imageUrl: String?) {
    setImageURI(Uri.parse(imageUrl))
}
fun ImageView.load(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context)
            .load(it)
            .into(this)
    }
}
