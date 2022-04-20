package com.iagora.wingman.auth.token.domain.use_case.get_cache_token_use_case

import com.iagora.wingman.auth.token.domain.repository.TokenDataStorePreferencesRepository

class GetCacheTokenUseCase(private val tokenDataStorePreferencesRepository: TokenDataStorePreferencesRepository) {
    suspend operator fun invoke(): String {
        val result = tokenDataStorePreferencesRepository.getToken()
        val token = result.getOrNull().orEmpty()
        return token
    }
}