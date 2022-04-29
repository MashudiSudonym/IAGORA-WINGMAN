package com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case

import com.iagora.wingman.auth.otp.domain.model.VerifyOTPResult
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.common.util.Resource
import com.iagora.wingman.common.util.UIText
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VerifyOTPWithSaveCredentialsUseCase(
    private val otpRepository: OTPRepository,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        otpCode: String
    ): Flow<Resource<VerifyOTPResult>> {
        val verifyOTP = otpRepository.verifyOTP(phoneNumber, otpCode)

        return flow {
            verifyOTP.collect { result ->
                when (result) {
                    is Resource.Error -> emit(
                        Resource.Error(
                            result.message ?: UIText.unknownError()
                        )
                    )
                    is Resource.Loading -> emit(Resource.Loading(true))
                    is Resource.Success -> {
                        val token = result.data?.result?.refreshToken
                        val userId = result.data?.result?.wingmanId
                        val isCompleteRegister = result.data?.result?.isCompleteRegister

                        dataStorePreferencesRepository.setToken(token ?: "")
                        dataStorePreferencesRepository.setUserId(userId ?: "")
                        dataStorePreferencesRepository.setUserCompleteDataStatus(
                            isCompleteRegister ?: false
                        )

                        emit(
                            Resource.Success(
                                VerifyOTPResult(
                                    wingmanId = userId.toString(),
                                    isCompleteRegister = isCompleteRegister ?: false,
                                    refreshToken = token.toString()
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}