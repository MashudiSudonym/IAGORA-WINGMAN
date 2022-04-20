package com.iagora.wingman.auth.otp.data.repository

import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.data.mapper.toOTPPhoneNumberDTO
import com.iagora.wingman.auth.otp.data.mapper.toOTPResponse
import com.iagora.wingman.auth.otp.data.remote.OtpAPI
import com.iagora.wingman.auth.otp.domain.model.OTPPhoneNumber
import com.iagora.wingman.auth.otp.domain.model.OTPResponse
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

class OTPRepositoryImpl(private val otpAPI: OtpAPI) : OTPRepository {
    override suspend fun postOTP(phoneNumber: String): Flow<Resource<OTPResponse>> {
        return flow {
            val requestPhoneNumber = OTPPhoneNumber(phoneNumber).toOTPPhoneNumberDTO()

            try {
                val sendOTP = otpAPI.requestOTP(requestPhoneNumber)

                emit(Resource.Success(sendOTP.toOTPResponse()))
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
                        message = UIText.StringResource(R.string.internet_problem)
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