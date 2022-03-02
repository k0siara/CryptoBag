package com.patrykkosieradzki.cryptobag.feature.coindetails.data

import com.patrykkosieradzki.cryptobag.feature.coindetails.domain.GetCoinDetailsUseCase
import com.patrykkosieradzki.cryptobag.feature.coindetails.domain.GetCoinDetailsUseCaseImpl
import com.patrykkosieradzki.feature.home.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoinDetailsModule {

    @Provides
    fun provideGetCoinDetailsUseCase(
        coinRepository: CoinRepository
    ): GetCoinDetailsUseCase {
        return GetCoinDetailsUseCaseImpl(coinRepository)
    }
}