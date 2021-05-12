package com.app.githubdemo.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class ImageUtils {

    fun loadImageWithUrl(
        imageUrl: String,
        imageView: ImageView,
        placeHolder: Int,
        errorPlaceHolder: Int
    ) {
        Glide.with(imageView).asBitmap()
            .load(imageUrl)
            .apply {
                if (placeHolder != -1)
                    placeholder(placeHolder)

                if (errorPlaceHolder != -1)
                    error(errorPlaceHolder)
            }.into(
                object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        imageView.setImageBitmap(resource)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                    }

                })
    }
}