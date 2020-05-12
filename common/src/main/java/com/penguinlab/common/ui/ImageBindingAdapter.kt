package com.penguinlab.common.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.penguinlab.common.R


object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("$imageUrl")
            .error(R.drawable.ic_error_primary_24dp)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrl", "placeHolder")
    fun setProfileUrl(imageView: ImageView, imageUrl: String?, placeHolder: Drawable) {
        val glide = Glide.with(imageView.context)
        glide.load("$imageUrl")
            .placeholder(placeHolder)
            .error(R.drawable.ic_error_primary_24dp)
            .circleCrop()
            .into(imageView)
    }


    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}