package com.iagora.wingman.auth.credential.domain.repository

interface CredentialDataStorePreferencesRepository {
    suspend fun setToken(token: String)
    suspend fun getToken(): Result<String>
    suspend fun setUserId(token: String)
    suspend fun getUserId(): Result<String>
}