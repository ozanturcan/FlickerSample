package com.penguinlab.data.feed.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @field:PrimaryKey
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: Int,
    var title: String?,
    var ispublic: Short,
    var url_n: String?,
    var width_n: String?,
    var height_n: String?
)