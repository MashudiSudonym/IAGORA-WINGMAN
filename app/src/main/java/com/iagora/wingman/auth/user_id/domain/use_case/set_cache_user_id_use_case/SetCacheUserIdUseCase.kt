package com.iagora.wingman.auth.user_id.domain.use_case.set_cache_user_id_use_case

import com.iagora.wingman.auth.user_id.domain.repository.UserIdDataStorePreferencesRepository

class SetCacheUserIdUseCase(private val userIdDataStorePreferencesRepository: UserIdDataStorePreferencesRepository) {
    suspend operator fun invoke(token: String) {
        return userIdDataStorePreferencesRepository.setUserId(token)
    }
}