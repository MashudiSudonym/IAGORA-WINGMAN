package com.iagora.wingman.auth.token.domain.repository

interface TokenDataStorePreferencesRepository {
    suspend fun setToken(token: String)
    suspend fun getToken(): Result<String>
}