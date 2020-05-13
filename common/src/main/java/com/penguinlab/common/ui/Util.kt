package com.penguinlab.common.ui

import com.penguinlab.common.BuildConfig
import com.penguinlab.model.PhotoItem

class Util {

    companion object {
        fun ConvertStaticPhotoUrl(photoItem: PhotoItem): String {
            return "https://farm${photoItem.farm}.staticflickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}_b.jpg"
        }

        fun ConvertStaticProfileImageUrl(photoItem: PhotoItem): String {
            return "http://farm${photoItem.farm}.staticflickr.com/${photoItem.server}/buddyicons/${photoItem.owner}.jpg"
        }


        fun CheckNewVersionAvailable(versionToCompare: String): Boolean {
            var isCurrentVersionOld = false
            val currentVersionArray = BuildConfig.VERSION_NAME.split(".")
            val versionToCompareArray = versionToCompare.split(".")

            for ((index, value) in currentVersionArray.withIndex()) {
                if (value < versionToCompareArray[index]) {
                    isCurrentVersionOld = true
                }
            }
            return isCurrentVersionOld
        }
    }
}