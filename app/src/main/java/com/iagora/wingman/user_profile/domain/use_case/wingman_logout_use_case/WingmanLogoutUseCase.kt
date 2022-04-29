package com.iagora.wingman.user_profile.domain.use_case.wingman_logout_use_case

import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WingmanLogoutUseCase(private val dataStorePreferencesRepository: DataStorePreferencesRepository) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading(true))

            try {
                delay(500L)

                with(dataStorePreferencesRepository) {
                    setUserId("")
                    setToken("")
                    setUserCompleteDataStatus(false)
                }

                emit(Resource.Loading(false))
                emit(Resource.Success(true))
            } catch (e: UnknownError) {
                emit(Resource.Error(UIText.unknownError()))
            }
        }
    }
}