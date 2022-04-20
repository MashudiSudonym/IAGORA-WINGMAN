package com.iagora.wingman.auth.otp.data.repository

import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.data.mapper.toOTPCodePhoneNumberDTO
import com.iagora.wingman.auth.otp.data.mapper.toOTPPhoneNumberDTO
import com.iagora.wingman.auth.otp.data.mapper.toOTPResponse
import com.iagora.wingman.auth.otp.data.mapper.toVerifyOTPResponse
import com.iagora.wingman.auth.otp.data.remote.OtpAPI
import com.iagora.wingman.auth.otp.domain.model.OTPCodePhoneNumber
import com.iagora.wingman.auth.otp.domain.model.OTPPhoneNumber
import com.iagora.wingman.auth.otp.domain.model.SendOTPResponse
import com.iagora.wingman.auth.otp.domain.model.VerifyOTPResponse
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
    override suspend fun sendOTP(phoneNumber: String): Flow<Resource<SendOTPResponse>> {
        return flow {
            val otpPhoneNumberBodyData = OTPPhoneNumber(phoneNumber).toOTPPhoneNumberDTO()

            try {
                val sendOTP = otpAPI.requestOTP(otpPhoneNumberBodyData)

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

    override suspend fun verifyOTP(
        phoneNumber: String,
        otpCode: String
    ): Flow<Resource<VerifyOTPResponse>> {
        return flow {
            emit(Resource.Loading(true))

            val otpCodePhoneNumberBodyData =
                OTPCodePhoneNumber(phoneNumber, otpCode).toOTPCodePhoneNumberDTO()

            try {
                val verifyOTP = otpAPI.verifyOTP(otpCodePhoneNumberBodyData)

                emit(Resource.Success(verifyOTP.toVerifyOTPResponse()))

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