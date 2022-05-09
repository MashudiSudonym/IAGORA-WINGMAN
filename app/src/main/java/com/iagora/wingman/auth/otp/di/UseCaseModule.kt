package com.iagora.wingman.auth.otp.di

import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import com.iagora.wingman.auth.otp.domain.use_case.count_down_timer_use_case.CountDownTimerUseCase
import com.iagora.wingman.auth.otp.domain.use_case.field_validation_use_case.PhoneNumberFieldValidationUseCase
import com.iagora.wingman.auth.otp.domain.use_case.is_aunthenticated_use_case.IsAuthenticatedUseCase
import com.iagora.wingman.auth.otp.domain.use_case.is_wingman_complete_data_use_case.IsWingmanCompleteDataUseCase
import com.iagora.wingman.auth.otp.domain.use_case.send_otp_use_case.SendOTPUseCase
import com.iagora.wingman.auth.otp.domain.use_case.verify_otp_use_case.VerifyOTPWithSaveCredentialsUseCase
import com.iagora.wingman.auth.request_token.domain.repository.RequestTokenRepository
import com.iagora.wingman.auth.request_token.domain.use_case.save_token_to_data_store_use_case.SaveTokenToDataStoreUseCase
import com.iagora.wingman.data_store.domain.repository.DataStorePreferencesRepository
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
    fun provideIsWingmanCompleteDataUseCase(dataStorePreferencesRepository: DataStorePreferencesRepository): IsWingmanCompleteDataUseCase {
        return IsWingmanCompleteDataUseCase(dataStorePreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideIsAuthenticatedUseCase(
        saveTokenToDataStoreUseCase: SaveTokenToDataStoreUseCase,
        dataStorePreferencesRepository: DataStorePreferencesRepository,
        requestTokenRepository: RequestTokenRepository
    ): IsAuthenticatedUseCase {
        return IsAuthenticatedUseCase(
            saveTokenToDataStoreUseCase,
            dataStorePreferencesRepository,
            requestTokenRepository
        )
    }

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

    @Provides
    @Singleton
    fun providePhoneNumberFieldValidationUseCase(): PhoneNumberFieldValidationUseCase {
        return PhoneNumberFieldValidationUseCase()
    }
}