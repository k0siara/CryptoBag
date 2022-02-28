package com.patrykkosieradzki.common.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinRankingService {
    @GET("coins")
    suspend fun getCoins(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): CoinRankingApiResponse<CoinsResponse>

    @GET("coin/{coinId}")
    suspend fun getCoinDetails(
        @Path("coinId") coinId: String
    ): CoinRankingApiResponse<CoinDetailsResponse>
}