package com.penguinlab.common

import androidx.annotation.StringDef

object ForceUpdateDef {

    const val KEY_FORCE_UPDATE_REQUIRED = "force_update_required"
    const val KEY_CURRENT_VERSION = "force_update_current_version"
    const val KEY_STORE_URL = "force_update_store_url"
    private const val FORCE_UPDATE_CACHE_EXP: Long = 600 // fetch every 10 minutes

    @StringDef(KEY_FORCE_UPDATE_REQUIRED, KEY_CURRENT_VERSION, KEY_STORE_URL)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ForceUpdateDef


    fun getCacheExpiration(): Long {
        // If is developer mode, cache expiration set to 0, in order to test
        if (BuildConfig.DEBUG) {
            return 0
        }
        return FORCE_UPDATE_CACHE_EXP
    }
}

class ForceUpdateModel(
    val isForceUpdateRequired: Boolean,
    val versionOnGooglePlay: String,
    val googlePlayUrl: String
)