package com.patrykkosieradzki.feature.home.domain

import com.patrykkosieradzki.feature.home.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins(): List<Coin>
    suspend fun getCoinDetails(id: String): Coin
}
