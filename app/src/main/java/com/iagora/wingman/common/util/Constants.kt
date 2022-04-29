package com.iagora.wingman.common.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.iagora.wingman.BuildConfig


object Constants {
    const val BASE_URL_API = BuildConfig.BASE_URL_API
    const val X_REFRESH_TOKEN = "x-refresh-token"
    const val BEARER = "Bearer"
    val TOKEN = stringPreferencesKey(name = "x-token")
    val USERID = stringPreferencesKey(name = "wingmanId")
    val USERCOMPLETEDATASTATUS = booleanPreferencesKey(name = "user-complete-data-status")
    const val START_COUNT_DOWN = 30
    const val HOME = "Home"
    const val CUSTOMER_CARE = "Customer Care"
    const val PROFILE = "Profile"
    const val REQUEST_TOKEN_WORKER = "RequestTokenWorker"
    const val KTP = "ktp"
    const val SKCK = "skck"
    const val BANK = "bank"
    const val NUMBER_BALANCE_ACCOUNT = "no_rek"
    const val NAME_BALANCE_ACCOUNT = "nama_rek"
}