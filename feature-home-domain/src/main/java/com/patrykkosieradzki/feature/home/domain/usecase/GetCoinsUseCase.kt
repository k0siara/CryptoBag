package com.patrykkosieradzki.feature.home.domain.usecase

import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.feature.home.domain.repository.CoinRepository

interface GetCoinsUseCase {
    suspend fun invoke(pageNumber: Int, loadSize: Int): List<Coin>
}

class GetCoinsUseCaseImpl(
    private val coinRepository: CoinRepository
) : GetCoinsUseCase {
    override suspend fun invoke(pageNumber: Int, loadSize: Int): List<Coin> {
        return coinRepository.getCoins(pageNumber, loadSize)
    }
}
