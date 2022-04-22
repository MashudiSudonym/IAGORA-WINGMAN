package com.iagora.wingman.auth.credential.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository
import com.iagora.wingman.common.util.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CredentialDataStorePreferencesRepositoryImpl @Inject constructor(private val credentialDataStoragePreferences: DataStore<Preferences>) :
    CredentialDataStorePreferencesRepository {
    override suspend fun setToken(token: String) {
        Result.runCatching {
            credentialDataStoragePreferences.edit { preferences ->
                preferences[Constants.TOKEN] = token
            }
        }
    }

    override suspend fun getToken(): Result<String> {
        return Result.runCatching {
            val flow = credentialDataStoragePreferences.data.catch { exception ->
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

    override suspend fun setUserId(token: String) {
        Result.runCatching {
            credentialDataStoragePreferences.edit { preferences ->
                preferences[Constants.USERID] = token
            }
        }
    }

    override suspend fun getUserId(): Result<String> {
        return Result.runCatching {
            val flow = credentialDataStoragePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[Constants.USERID]
            }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }
}