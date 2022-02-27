package com.patrykkosieradzki.cryptobag.home.data

import com.patrykkosieradzki.cryptobag.common.remote.model.CoinResponse
import com.patrykkosieradzki.cryptobag.common.remote.service.CoinRankingService

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