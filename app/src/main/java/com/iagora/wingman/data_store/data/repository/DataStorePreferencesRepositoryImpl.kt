package com.iagora.wingman.data_store.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStorePreferencesRepositoryImpl @Inject constructor(private val credentialDataStoragePreferences: DataStore<Preferences>) :
    DataStorePreferencesRepository {
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

    override suspend fun setUserId(userId: String) {
        Result.runCatching {
            credentialDataStoragePreferences.edit { preferences ->
                preferences[Constants.USERID] = userId
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

    override suspend fun setUserCompleteDataStatus(userCompleteDataStatus: Boolean) {
        Result.runCatching {
            credentialDataStoragePreferences.edit { preferences ->
                preferences[Constants.USERCOMPLETEDATASTATUS] = userCompleteDataStatus
            }
        }
    }

    override suspend fun getUserCompleteDataStatus(): Result<Boolean> {
        return Result.runCatching {
            val flow = credentialDataStoragePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[Constants.USERCOMPLETEDATASTATUS]
            }
            val value = flow.firstOrNull() ?: false
            value
        }
    }
}