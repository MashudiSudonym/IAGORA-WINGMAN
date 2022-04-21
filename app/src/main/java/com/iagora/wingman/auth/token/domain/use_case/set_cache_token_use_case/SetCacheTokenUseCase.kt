package com.iagora.wingman.auth.token.domain.use_case.set_cache_token_use_case

import com.iagora.wingman.auth.token.domain.repository.TokenDataStorePreferencesRepository
import com.iagora.wingman.auth.user_id.domain.repository.UserIdDataStorePreferencesRepository

class SetCacheTokenUseCase(private val tokenDataStorePreferencesRepository: TokenDataStorePreferencesRepository) {
    suspend operator fun invoke(token: String) {
        return tokenDataStorePreferencesRepository.setToken(token)
    }
}