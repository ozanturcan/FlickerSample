package com.penguinlab.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.penguinlab.data.feed.entity.PhotoEntity


@Database(
    entities = [PhotoEntity::class],
    version = 0,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase()