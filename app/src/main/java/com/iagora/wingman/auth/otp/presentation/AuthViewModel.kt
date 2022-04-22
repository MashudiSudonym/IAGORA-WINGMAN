package com.iagora.wingman.auth.otp.presentation

import androidx.lifecycle.ViewModel
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPUseCase
import com.iagora.wingman.auth.credential.domain.use_case.get_cache_token_use_case.GetCacheTokenUseCase
import com.iagora.wingman.auth.credential.domain.use_case.set_cache_token_use_case.SetCacheTokenUseCase
import com.iagora.wingman.auth.credential.domain.use_case.get_cache_user_id_use_case.GetCacheUserIdUseCase
import com.iagora.wingman.auth.credential.domain.use_case.set_cache_user_id_use_case.SetCacheUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sendOTPUseCase: SendOTPUseCase,
    private val verifyOTPUseCase: VerifyOTPUseCase,
    private val getCacheTokenUseCase: GetCacheTokenUseCase,
    private val setCacheTokenUseCase: SetCacheTokenUseCase,
    private val getCacheUserIdUseCase: GetCacheUserIdUseCase,
    private val setCacheUserIdUseCase: SetCacheUserIdUseCase
) : ViewModel() {

}