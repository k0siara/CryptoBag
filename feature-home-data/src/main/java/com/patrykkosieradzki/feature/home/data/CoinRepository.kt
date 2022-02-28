package com.patrykkosieradzki.feature.home.data

import com.patrykkosieradzki.common.remote.CoinRankingService
import com.patrykkosieradzki.common.remote.CoinResponse

interface CoinRepository {
    suspend fun getCoins(): List<CoinResponse>
    suspend fun getCoinDetails(id: String): CoinResponse
}

class CoinApiRepository(
    private val coinRankingService: CoinRankingService
) : CoinRepository {
    override suspend fun getCoins() = coinRankingService.getCoins().data.coins
    override suspend fun getCoinDetails(id: String): CoinResponse {
        return coinRankingService.getCoinDetails(id).data.coin
    }
}