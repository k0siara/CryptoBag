package com.patrykkosieradzki.cryptobag.common.remote.model

import com.squareup.moshi.Json

data class CoinDetailsResponse (
    @Json(name = "coin") val coin: CoinResponse
)