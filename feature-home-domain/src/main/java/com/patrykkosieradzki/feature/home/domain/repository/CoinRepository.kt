package com.patrykkosieradzki.feature.home.domain.repository

import com.patrykkosieradzki.feature.home.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins(pageNumber: Int, loadSize: Int): List<Coin>
    suspend fun getCoinDetails(id: String): Coin
}
