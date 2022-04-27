package com.iagora.wingman.customer_care.domain.use_case.get_access_token_use_case

import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import com.iagora.wingman.customer_care.domain.model.AccessTokenData
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


// remove it!! just for test worker
class GetAccessTokenUseCase @Inject constructor(private val dataStorePreferencesRepository: DataStorePreferencesRepository) {
    suspend operator fun invoke(): Flow<Resource<AccessTokenData>> {
        val getToken = dataStorePreferencesRepository.getToken().getOrDefault("")

        return flow {
            emit(Resource.Loading(true))

            if (getToken != "") {
                emit(Resource.Loading(false))
                emit(Resource.Success(AccessTokenData(accessToken = getToken)))
            } else {
                emit(Resource.Loading(false))
                emit(Resource.Error(UIText.unknownError()))
            }
        }
    }
}