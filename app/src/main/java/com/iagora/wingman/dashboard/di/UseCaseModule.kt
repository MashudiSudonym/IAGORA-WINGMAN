package com.iagora.wingman.dashboard.di

import com.iagora.wingman.dashboard.domain.use_case.greeting_use_case.GreetingUseCase
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
    fun provideGreetingUseCase(): GreetingUseCase {
        return GreetingUseCase()
    }
}