package com.patrykkosieradzki.feature.home.domain.model

import com.patrykkosieradzki.cryptobag.utils.toNullableString
import java.text.DecimalFormat

data class Coin(
    val uuid: String?,
    val symbol: String?,
    val name: String?,
    val color: String?,
    val iconUrl: String?,
    val marketCap: String,
    val price: String,
    val listedAt: Long?,
    val tier: Long?,
    val change: String?,
    val rank: Long?,
    val sparkline: List<String>,
    val lowVolume: Boolean?,
    val coinrankingURL: String?,
    val the24HVolume: String?,
    val btcPrice: String?,
    val description: String?,
    val websiteURL: String?,
    val links: List<Link> = emptyList(),
    val supply: Supply?,
    val numberOfMarkets: Long?,
    val numberOfExchanges: Long?,
    val priceAt: Long?,
    val allTimeHigh: AllTimeHigh?
) {
    fun getFormattedPrice(): String = "$${PRICE_FORMAT.format(price.toBigDecimal())}"

    fun getChangeInPercentage(): String = "${change.toNullableString()}%"

    companion object {
        val PRICE_FORMAT = DecimalFormat("#,###.00")
    }
}