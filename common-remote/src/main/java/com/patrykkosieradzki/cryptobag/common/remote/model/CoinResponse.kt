package com.patrykkosieradzki.cryptobag.common.remote.model

import com.squareup.moshi.Json

data class CoinResponse (
    @Json(name = "uuid") val uuid: String?,
    @Json(name = "symbol") val symbol: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "color") val color: String?,
    @Json(name = "iconUrl") val iconUrl: String?,
    @Json(name = "marketCap") val marketCap: String,
    @Json(name = "price") val price: String,
    @Json(name = "listedAt") val listedAt: Long?,
    @Json(name = "tier") val tier: Long?,
    @Json(name = "change") val change: String?,
    @Json(name = "rank") val rank: Long?,
    @Json(name = "sparkline") val sparkline: List<String> = emptyList(),
    @Json(name = "lowVolume") val lowVolume: Boolean?,
    @Json(name = "coinrankingURL") val coinrankingURL: String?,
    @Json(name = "the24HVolume") val the24HVolume: String?,
    @Json(name = "btcPrice") val btcPrice: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "websiteURL") val websiteURL: String?,
    @Json(name = "links") val links: List<LinkResponse>?,
    @Json(name = "supply") val supply: SupplyResponse?,
    @Json(name = "numberOfMarkets") val numberOfMarkets: Long?,
    @Json(name = "numberOfExchanges") val numberOfExchanges: Long?,
    @Json(name = "priceAt") val priceAt: Long?,
    @Json(name = "allTimeHigh") val allTimeHigh: AllTimeHighResponse?
)

data class AllTimeHighResponse (
    @Json(name = "price") val price: String,
    @Json(name = "timestamp") val timestamp: Long
)

data class LinkResponse (
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String
)

data class SupplyResponse (
    @Json(name = "confirmed") val confirmed: Boolean,
    @Json(name = "total") val total: String,
    @Json(name = "circulating") val circulating: String
)
