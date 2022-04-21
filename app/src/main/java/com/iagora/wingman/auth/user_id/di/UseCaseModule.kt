package com.iagora.wingman.auth.user_id.di

import com.iagora.wingman.auth.user_id.domain.repository.UserIdDataStorePreferencesRepository
import com.iagora.wingman.auth.user_id.domain.use_case.get_cache_user_id_use_case.GetCacheUserIdUseCase
import com.iagora.wingman.auth.user_id.domain.use_case.set_cache_user_id_use_case.SetCacheUserIdUseCase
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
    fun provideSetCacheUserIdUseCase(userIdDataStorePreferencesRepository: UserIdDataStorePreferencesRepository): SetCacheUserIdUseCase {
        return SetCacheUserIdUseCase(userIdDataStorePreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideGetCacheUserIdUseCase(userIdDataStorePreferencesRepository: UserIdDataStorePreferencesRepository): GetCacheUserIdUseCase {
        return GetCacheUserIdUseCase(userIdDataStorePreferencesRepository)
    }
}