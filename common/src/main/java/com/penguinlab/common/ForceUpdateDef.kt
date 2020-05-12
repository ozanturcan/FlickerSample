package com.penguinlab.common

import androidx.annotation.StringDef

object ForceUpdateDef {

    const val KEY_UPDATE_ENABLED = "force_update_required"
    const val KEY_CURRENT_VERSION = "force_update_current_version"
    const val KEY_STORE_URL = "force_update_store_url"

    @StringDef(KEY_UPDATE_ENABLED, KEY_CURRENT_VERSION, KEY_STORE_URL)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ForceUpdateDef
}