package com.iagora.wingman.auth.user_id.domain.repository

interface UserIdDataStorePreferencesRepository {
    suspend fun setUserId(token: String)
    suspend fun getUserId(): Result<String>
}