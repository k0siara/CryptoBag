package com.patrykkosieradzki.feature.home.data

import com.patrykkosieradzki.common.remote.service.CoinRankingService
import com.patrykkosieradzki.feature.home.domain.repository.CoinRepository
import com.patrykkosieradzki.feature.home.domain.usecase.GetCoinsUseCase
import com.patrykkosieradzki.feature.home.domain.usecase.GetCoinsUseCaseImpl
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

    @Singleton
    @Provides
    fun provideGetCoinsUseCase(coinRepository: CoinRepository): GetCoinsUseCase {
        return GetCoinsUseCaseImpl(coinRepository)
    }
}