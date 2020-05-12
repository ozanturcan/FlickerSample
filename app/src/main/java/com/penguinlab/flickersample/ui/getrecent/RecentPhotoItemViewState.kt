package com.penguinlab.flickersample.ui.getrecent

import com.penguinlab.model.PhotoItem

class RecentPhotoItemViewState(private val photoItem: PhotoItem) {
    fun getImageUrl(): String =
        "https://farm${photoItem.farm}.staticflickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}_b.jpg"

    fun getProfileImageUrl(): String =
        "http://farm${photoItem.farm}.staticflickr.com/${photoItem.server}/buddyicons/${photoItem.owner}.jpg"

    fun getTitle() = photoItem.title
}