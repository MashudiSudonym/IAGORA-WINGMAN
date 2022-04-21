package com.iagora.wingman.auth.user_id.domain.use_case.get_cache_user_id_use_case

import com.iagora.wingman.auth.user_id.domain.repository.UserIdDataStorePreferencesRepository

class GetCacheUserIdUseCase(private val userIdDataStorePreferencesRepository: UserIdDataStorePreferencesRepository) {
    suspend operator fun invoke(): String {
        val result = userIdDataStorePreferencesRepository.getUserId()
        val userId = result.getOrNull().orEmpty()
        return userId
    }
}