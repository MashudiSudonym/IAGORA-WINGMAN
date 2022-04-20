package com.iagora.wingman.auth.otp.di

import com.iagora.wingman.auth.otp.data.remote.OtpAPI
import com.iagora.wingman.auth.otp.data.repository.OTPRepositoryImpl
import com.iagora.wingman.auth.otp.domain.repository.OTPRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideOTPRepository(otpAPI: OtpAPI): OTPRepository {
        return OTPRepositoryImpl(otpAPI)
    }
}