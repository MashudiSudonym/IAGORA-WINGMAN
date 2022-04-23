package com.iagora.wingman.auth.otp.data.mapper

import com.iagora.wingman.auth.otp.data.remote.dao.VerifyOTPResultDTO
import com.iagora.wingman.auth.otp.domain.model.VerifyOTPResult

fun VerifyOTPResultDTO.toVerifyOTPResult(): VerifyOTPResult {
    return VerifyOTPResult(
        wingmanId = wingmanId,
        isCompleteRegister = isCompleteRegister,
        refreshToken = refreshToken
    )
}

fun VerifyOTPResult.toVerifyOTPResultDTO(): VerifyOTPResultDTO {
    return VerifyOTPResultDTO(
        wingmanId = wingmanId,
        isCompleteRegister = isCompleteRegister,
        refreshToken = refreshToken
    )
}