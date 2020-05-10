package com.penguinlab.data.di.module

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.penguinlab.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "flicker-sample.db")
            .build()
    }


}