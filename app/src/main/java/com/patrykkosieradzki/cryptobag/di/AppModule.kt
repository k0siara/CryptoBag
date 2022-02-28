package com.patrykkosieradzki.cryptobag.di

import com.patrykkosieradzki.cryptobag.BuildConfig
import com.patrykkosieradzki.cryptobag.CryptoBagAppConfiguration
import com.patrykkosieradzki.cryptobag.utils.AppConfiguration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppConfiguration(): AppConfiguration {
        return CryptoBagAppConfiguration()
    }

    @Provides
    @Named("coin-ranking-api-key")
    fun provideCoinRankingApiKey(): String = BuildConfig.API_KEY
}