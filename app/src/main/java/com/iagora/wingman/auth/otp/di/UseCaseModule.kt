package com.iagora.wingman.auth.otp.di

import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.auth.otp.domain.use_case.count_down_timer_use_case.CountDownTimerUseCase
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPWithSaveCredentialsUseCase
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
    fun provideVerifyOTPWithSaveCredentialUseCase(
        otpRepository: OTPRepository,
        dataStorePreferencesRepository: DataStorePreferencesRepository
    ): VerifyOTPWithSaveCredentialsUseCase {
        return VerifyOTPWithSaveCredentialsUseCase(
            otpRepository,
            dataStorePreferencesRepository
        )
    }

    @Provides
    @Singleton
    fun providerCountDownTimerUseCase(): CountDownTimerUseCase {
        return CountDownTimerUseCase()
    }
}