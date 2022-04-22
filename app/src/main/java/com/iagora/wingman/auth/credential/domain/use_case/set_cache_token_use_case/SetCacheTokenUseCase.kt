package com.iagora.wingman.auth.credential.domain.use_case.set_cache_token_use_case

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository

class SetCacheTokenUseCase(private val credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository) {
    suspend operator fun invoke(token: String) {
        return credentialDataStorePreferencesRepository.setToken(token)
    }
}