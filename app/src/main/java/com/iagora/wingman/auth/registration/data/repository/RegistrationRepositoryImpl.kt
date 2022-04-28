package com.iagora.wingman.auth.registration.data.repository

import com.iagora.wingman.R
import com.iagora.wingman.auth.registration.data.mapper.toCompleteWingmanDetailDataDTO
import com.iagora.wingman.auth.registration.data.mapper.toCompleteWingmanDetailResponse
import com.iagora.wingman.auth.registration.data.remote.RegistrationAPI
import com.iagora.wingman.auth.registration.domain.model.CompleteWingmanDetailData
import com.iagora.wingman.auth.registration.domain.model.CompleteWingmanDetailResponse
import com.iagora.wingman.auth.registration.domain.repository.RegistrationRepository
import com.iagora.wingman.common.util.Constants
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

class RegistrationRepositoryImpl(private val registrationAPI: RegistrationAPI) :
    RegistrationRepository {
    override suspend fun putWingmanDetail(
        name: String,
        email: String,
        address: String,
        city: String,
        token: String
    ): Flow<Resource<CompleteWingmanDetailResponse>> {
        return flow {
            emit(Resource.Loading(true))

            val completeWingmanDetailData = CompleteWingmanDetailData(
                name,
                email,
                address,
                city
            ).toCompleteWingmanDetailDataDTO()
            val accessToken = "${Constants.BEARER} $token"

            try {
                val sendWingmanDetailData =
                    registrationAPI.completeWingmanDetail(accessToken, completeWingmanDetailData)

                emit(Resource.Success(sendWingmanDetailData.toCompleteWingmanDetailResponse()))
                emit(Resource.Loading(false))
            } catch (e: HttpException) {
                Timber.e(e.message())
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.internet_problem)
                    )
                )
                emit(Resource.Loading(false))
            } catch (e: IOException) {
                Timber.e(e)
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.error_upload_failed)
                    )
                )
                emit(Resource.Loading(false))
            } catch (e: UnknownHostException) {
                Timber.e(e)
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.error_unknown)
                    )
                )
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun putWingmanDocument(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        bank: RequestBody,
        numberBalanceAccount: RequestBody,
        nameBalanceAccount: RequestBody,
        token: String
    ): Flow<Resource<CompleteWingmanDetailResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val accessToken = "${Constants.BEARER} $token"

            try {
                val sendWingmanDocumentData =
                    registrationAPI.completeWingmanDocument(
                        accessToken,
                        ktp,
                        skck,
                        bank,
                        numberBalanceAccount,
                        nameBalanceAccount
                    )

                emit(Resource.Success(sendWingmanDocumentData.toCompleteWingmanDetailResponse()))
                emit(Resource.Loading(false))
            } catch (e: HttpException) {
                Timber.e(e.message())
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.internet_problem)
                    )
                )
                emit(Resource.Loading(false))
            } catch (e: IOException) {
                Timber.e(e)
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.error_upload_failed)
                    )
                )
                emit(Resource.Loading(false))
            } catch (e: UnknownHostException) {
                Timber.e(e)
                emit(
                    Resource.Error(
                        message = UIText.StringResource(R.string.error_unknown)
                    )
                )
                emit(Resource.Loading(false))
            }
        }
    }
}