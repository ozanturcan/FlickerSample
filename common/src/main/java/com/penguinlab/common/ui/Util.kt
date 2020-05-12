package com.penguinlab.common.ui

import com.penguinlab.model.PhotoItem

class Util {

    companion object {
        fun ConvertStaticPhotoUrl(photoItem: PhotoItem): String =
            "https://farm${photoItem.farm}.staticflickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}_b.jpg"

        fun ConvertStaticProfileImageUrl(photoItem: PhotoItem): String =
            "http://farm${photoItem.farm}.staticflickr.com/${photoItem.server}/buddyicons/${photoItem.owner}.jpg"
    }
}