package com.iagora.wingman.auth.otp.data.repository

import com.iagora.wingman.R
import com.iagora.wingman.auth.otp.data.mapper.toOTPCodePhoneNumberDataDTO
import com.iagora.wingman.auth.otp.data.mapper.toOTPPhoneNumberDataDTO
import com.iagora.wingman.auth.otp.data.mapper.toOTPResponse
import com.iagora.wingman.auth.otp.data.mapper.toVerifyOTPResponse
import com.iagora.wingman.auth.otp.data.remote.OtpAPI
import com.iagora.wingman.auth.otp.domain.model.OTPCodePhoneNumberData
import com.iagora.wingman.auth.otp.domain.model.OTPPhoneNumberData
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
            val otpPhoneNumberBodyData = OTPPhoneNumberData(phoneNumber).toOTPPhoneNumberDataDTO()

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
                OTPCodePhoneNumberData(phoneNumber, otpCode).toOTPCodePhoneNumberDataDTO()

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
                        message = UIText.StringResource(R.string.error_wrong_otp_code)
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