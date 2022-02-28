package com.patrykkosieradzki.common.remote

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