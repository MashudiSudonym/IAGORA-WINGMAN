package com.iagora.wingman.common.util

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iagora.wingman.BuildConfig


object Constants {
    const val BASE_URL_API = BuildConfig.BASE_URL_API
    const val CLIENT_TYPE_KEY = "client-type"
    const val CLIENT_TYPE_VALUE = "wingman"
    const val X_REFRESH_TOKEN = "x-refresh-token"
    const val BEARER = "Bearer"
    val TOKEN = stringPreferencesKey(name = "x-token")
    val USERID = stringPreferencesKey(name = "wingmanId")
    const val START_COUNT_DOWN = 30
}