package com.penguinlab.data.di.module

import android.app.Application
import androidx.annotation.NonNull
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.penguinlab.common.ForceUpdateDef
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class FirebaseModule {


    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@NonNull application: Application): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {

        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        setDefaultValueMap(firebaseRemoteConfig)

        firebaseRemoteConfig.fetch(600) // fetch every 10 minutes
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("remote config is fetched.")
                    firebaseRemoteConfig.fetchAndActivate()
                }
            }

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)

        return firebaseRemoteConfig

    }

    private fun setDefaultValueMap(config: FirebaseRemoteConfig) {
        val defaults: MutableMap<String, Any> = mutableMapOf()
        defaults[ForceUpdateDef.KEY_STORE_URL] = "http://"
        defaults[ForceUpdateDef.KEY_CURRENT_VERSION] = "1"
        defaults[ForceUpdateDef.KEY_UPDATE_ENABLED] = true

        config.setDefaultsAsync(defaults as Map<String, Any>)
    }

}
