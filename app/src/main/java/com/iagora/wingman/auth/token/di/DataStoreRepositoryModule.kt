package com.iagora.wingman.auth.token.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.iagora.wingman.auth.token.data.repository.TokenDataStorePreferencesRepositoryImpl
import com.iagora.wingman.auth.token.domain.repository.TokenDataStorePreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.iagora.wingman.token_data_store"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTokenDataStorePreferencesRepository(tokenDataStorePreferencesRepositoryImpl: TokenDataStorePreferencesRepositoryImpl): TokenDataStorePreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideTokenDataStorePreferences(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
            return applicationContext.tokenDataStore
        }
    }
}