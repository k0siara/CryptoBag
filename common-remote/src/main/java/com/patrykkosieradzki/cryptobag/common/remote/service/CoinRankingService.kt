package com.patrykkosieradzki.cryptobag.common.remote.service

import com.patrykkosieradzki.cryptobag.common.remote.model.CoinDetailsResponse
import com.patrykkosieradzki.cryptobag.common.remote.model.CoinRankingApiResponse
import com.patrykkosieradzki.cryptobag.common.remote.model.CoinsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRankingService {
    @GET("coins")
    suspend fun getCoins(): CoinRankingApiResponse<CoinsResponse>

    @GET("coin/{coinId}")
    suspend fun getCoinDetails(
        @Path("coinId") coinId: String
    ): CoinRankingApiResponse<CoinDetailsResponse>
}