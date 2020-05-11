package com.penguinlab.model


import com.google.gson.annotations.SerializedName


data class PhotoListResult(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("perpage")
    val perpage: Int?,
    @SerializedName("photo")
    val photoList: List<PhotoItem?>,
    @SerializedName("total")
    val total: String?
)