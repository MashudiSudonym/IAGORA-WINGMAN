package com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case

import com.iagora.wingman.auth.credential.domain.repository.CredentialDataStorePreferencesRepository
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.common.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VerifyOTPWithSaveCredentialsUseCase(
    private val otpRepository: OTPRepository,
    private val credentialDataStorePreferencesRepository: CredentialDataStorePreferencesRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        otpCode: String
    ): Flow<Resource<Boolean>> {
        val verifyOTP = otpRepository.verifyOTP(phoneNumber, otpCode)

        return flow {
            verifyOTP.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        emit(Resource.Loading(true))
                        emit(Resource.Success(false))
                        emit(Resource.Loading(false))
                    }
                    is Resource.Loading -> {
                        emit(Resource.Loading(true))
                        emit(Resource.Loading(false))
                    }
                    is Resource.Success -> {
                        emit(Resource.Loading(true))

                        val token = result.data?.result?.refreshToken
                        val userId = result.data?.result?.wingmanId

                        credentialDataStorePreferencesRepository.setToken(token ?: "")
                        credentialDataStorePreferencesRepository.setUserId(userId ?: "")

                        emit(Resource.Success(true))
                        emit(Resource.Loading(false))
                    }
                }
            }
        }
    }
}