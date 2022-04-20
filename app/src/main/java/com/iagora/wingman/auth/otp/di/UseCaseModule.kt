package com.iagora.wingman.auth.otp.di

import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideSendOTPUseCase(
        otpRepository: OTPRepository
    ): SendOTPUseCase {
        return SendOTPUseCase(otpRepository)
    }

    @Provides
    @Singleton
    fun provideVerifyOTPUseCase(otpRepository: OTPRepository): VerifyOTPUseCase {
        return VerifyOTPUseCase(otpRepository)
    }
}