package com.iagora.wingman.auth.token.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.iagora.wingman.auth.token.domain.repository.TokenDataStorePreferencesRepository
import com.iagora.wingman.common.util.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class TokenDataStorePreferencesRepositoryImpl @Inject constructor(private val tokenDataStoragePreferences: DataStore<Preferences>) :
    TokenDataStorePreferencesRepository {
    override suspend fun setToken(token: String) {
        Result.runCatching {
            tokenDataStoragePreferences.edit { preferences ->
                preferences[Constants.TOKEN] = token
            }
        }
    }

    override suspend fun getToken(): Result<String> {
        return Result.runCatching {
            val flow = tokenDataStoragePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[Constants.TOKEN]
            }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }
}