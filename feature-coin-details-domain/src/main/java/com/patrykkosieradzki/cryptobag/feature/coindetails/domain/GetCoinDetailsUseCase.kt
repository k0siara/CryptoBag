package com.patrykkosieradzki.cryptobag.feature.coindetails.domain

import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.feature.home.domain.repository.CoinRepository

interface GetCoinDetailsUseCase {
    suspend fun invoke(coinId: String): Coin
}

class GetCoinDetailsUseCaseImpl(
    private val coinRepository: CoinRepository
) : GetCoinDetailsUseCase {
    override suspend fun invoke(coinId: String): Coin {
        return coinRepository.getCoinDetails(coinId)
    }
}
