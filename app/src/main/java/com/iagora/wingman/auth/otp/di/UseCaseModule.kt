package com.iagora.wingman.auth.otp.di

import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.auth.otp.domain.use_case.post_otp_use_case.PostOTPUseCase
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
    fun providePostOTPUseCase(
        otpRepository: OTPRepository
    ): PostOTPUseCase {
        return PostOTPUseCase(otpRepository)
    }
}