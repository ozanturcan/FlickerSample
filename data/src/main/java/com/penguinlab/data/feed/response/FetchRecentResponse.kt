package com.penguinlab.data.feed.response

import com.google.gson.annotations.SerializedName
import com.penguinlab.model.PhotoListResult

data class FetchRecentResponse(
    @SerializedName("photos")
    val photoListResult: PhotoListResult?,
    @SerializedName("stat")
    val stat: String?
)