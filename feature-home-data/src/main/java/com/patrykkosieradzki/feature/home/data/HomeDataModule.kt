package com.patrykkosieradzki.feature.home.data

import com.patrykkosieradzki.common.remote.CoinRankingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataModule {

    @Singleton
    @Provides
    fun provideCoinRepository(coinRankingService: CoinRankingService): CoinRepository {
        return CoinApiRepository(coinRankingService)
    }
}