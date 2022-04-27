package com.iagora.wingman.auth.request_token.data.repository

import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.data.remote.RequestTokenAPI
import com.iagora.wingman.auth.request_token.data.mapper.toRequestTokenResponse
import com.iagora.wingman.auth.request_token.domain.model.RequestTokenResponse
import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

class RequestTokenRepositoryImpl(private val requestTokenAPI: RequestTokenAPI) :
    RequestTokenRepository {
    override suspend fun RequestToken(token: String): Flow<Resource<RequestTokenResponse>> {
        return flow {
            try {
                val bearerToken = "${Constants.BEARER} $token"
                val requestToken = requestTokenAPI.getAccessToken(bearerToken)

                emit(Resource.Success(requestToken.toRequestTokenResponse()))
            } catch (e: HttpException) {
                Timber.e(e.message())
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.internet_problem)
                    )
                )
            } catch (e: IOException) {
                Timber.e(e)
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.error_access_token_error)
                    )
                )
            } catch (e: UnknownHostException) {
                Timber.e(e)
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.error_unknown)
                    )
                )
            }
        }
    }
}