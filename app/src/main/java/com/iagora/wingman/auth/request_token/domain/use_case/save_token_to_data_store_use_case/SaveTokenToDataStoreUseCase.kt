package com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case

import com.iagora.wingman.auth.request_token.domain.model.SaveRequestTokenStatusData
import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class SaveTokenToDataStoreUseCase(
    private val requestTokenRepository: RequestTokenRepository,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) {
    suspend operator fun invoke(): Flow<Resource<SaveRequestTokenStatusData>> {
        val getOldToken = dataStorePreferencesRepository.getToken().getOrDefault("")
        val requestNewToken = requestTokenRepository.RequestToken(getOldToken)

        return flow {
            requestNewToken.collect { result ->
                when (result) {
                    is Resource.Error -> emit(
                        Resource.Error(
                            result.message ?: UIText.unknownError()
                        )
                    )
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        val token = result.data?.result?.accessToken
                        val userId = result.data?.result?.wingmanId

                        dataStorePreferencesRepository.setToken(token ?: "")
                        dataStorePreferencesRepository.setUserId(userId ?: "")

                        emit(
                            Resource.Success(SaveRequestTokenStatusData(isTokenSaved = true))
                        )
                    }
                }
            }
        }
    }
}