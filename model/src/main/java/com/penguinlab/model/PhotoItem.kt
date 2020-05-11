package com.penguinlab.model


import com.google.gson.annotations.SerializedName

data class PhotoItem(
    @SerializedName("farm")
    val farm: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isfamily")
    val isfamily: Int?,
    @SerializedName("isfriend")
    val isfriend: Int?,
    @SerializedName("ispublic")
    val ispublic: Int?,
    @SerializedName("owner")
    val owner: String?,
    @SerializedName("secret")
    val secret: String?,
    @SerializedName("server")
    val server: String?,
    @SerializedName("title")
    val title: String?
)