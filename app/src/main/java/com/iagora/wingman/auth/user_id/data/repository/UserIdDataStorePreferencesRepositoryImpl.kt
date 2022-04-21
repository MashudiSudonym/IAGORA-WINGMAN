package com.iagora.wingman.auth.user_id.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.iagora.wingman.auth.user_id.domain.repository.UserIdDataStorePreferencesRepository
import com.iagora.wingman.common.util.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserIdDataStorePreferencesRepositoryImpl @Inject constructor(private val userIdDataStoragePreferences: DataStore<Preferences>) :
    UserIdDataStorePreferencesRepository {
    override suspend fun setUserId(token: String) {
        Result.runCatching {
            userIdDataStoragePreferences.edit { preferences ->
                preferences[Constants.USERID] = token
            }
        }
    }

    override suspend fun getUserId(): Result<String> {
        return Result.runCatching {
            val flow = userIdDataStoragePreferences.data.catch { exception ->
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