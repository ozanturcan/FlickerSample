package com.penguinlab.flickersample.ui.getrecent

import com.penguinlab.common.ui.Util
import com.penguinlab.model.PhotoItem

class RecentPhotoItemViewState(private val photoItem: PhotoItem) {
    fun getImageUrl(): String = Util.ConvertStaticPhotoUrl(photoItem)
    fun getProfileImageUrl(): String = Util.ConvertStaticProfileImageUrl(photoItem)

    fun getTitle() = photoItem.title
}