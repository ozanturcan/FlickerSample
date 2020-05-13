package com.penguinlab.data.di.module

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.penguinlab.common.ForceUpdateDef
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {


    @Provides
    @Singleton
    fun provideFirebaseAnalytics(application: Application): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {

        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()

        setDefaultValueMap(firebaseRemoteConfig)
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)

        return firebaseRemoteConfig

    }

    private fun setDefaultValueMap(config: FirebaseRemoteConfig) {
        val defaults: MutableMap<String, Any> = mutableMapOf()
        defaults[ForceUpdateDef.KEY_STORE_URL] = "http://"
        defaults[ForceUpdateDef.KEY_CURRENT_VERSION] = "1"
        defaults[ForceUpdateDef.KEY_FORCE_UPDATE_REQUIRED] = true

        config.setDefaultsAsync(defaults as Map<String, Any>)
    }

}
