package com.android.randomuser.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun ImageView.loadImageUrl(url: String) {
    Glide.with(this)
        .load(url)
        .thumbnail(0.1f)
        .into(this)
}