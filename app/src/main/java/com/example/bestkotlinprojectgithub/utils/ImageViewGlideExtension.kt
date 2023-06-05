package com.example.bestkotlinprojectgithub.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions

fun ImageView.glideLoad(
    url: String?,
    defaultError: Drawable?,
    defaultPlaceholder: Drawable?,
    scaleType: ImageView.ScaleType? = null
) {
    if (url.isNullOrEmpty()) {
        setImageDrawable(defaultPlaceholder)
        return
    }

    val options = if (
        this.scaleType == ImageView.ScaleType.CENTER_CROP
        || scaleType == ImageView.ScaleType.CENTER_CROP
        || scaleType == null
    ) {
        RequestOptions.centerCropTransform()
    } else RequestOptions.noTransformation()


    Glide.with(this)
        .load(GlideUrl(url))
        .override(this.width, this.height)
        .error(defaultError)
        .placeholder(defaultPlaceholder)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply(options)
        .into(this)
}

