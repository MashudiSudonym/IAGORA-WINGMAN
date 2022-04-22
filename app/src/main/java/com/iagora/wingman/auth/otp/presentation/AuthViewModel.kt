package com.iagora.wingman.auth.otp.presentation

import androidx.lifecycle.ViewModel
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPWithSaveCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sendOTPUseCase: SendOTPUseCase,
    private val verifyOTPWithSaveCredentialsUseCase: VerifyOTPWithSaveCredentialsUseCase,
) : ViewModel() {

}