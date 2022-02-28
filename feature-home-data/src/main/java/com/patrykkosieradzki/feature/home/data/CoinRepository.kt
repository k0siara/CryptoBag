package com.patrykkosieradzki.feature.home.data

import com.patrykkosieradzki.common.remote.model.AllTimeHighResponse
import com.patrykkosieradzki.common.remote.model.CoinResponse
import com.patrykkosieradzki.common.remote.model.LinkResponse
import com.patrykkosieradzki.common.remote.model.SupplyResponse
import com.patrykkosieradzki.common.remote.service.CoinRankingService
import com.patrykkosieradzki.feature.home.domain.repository.CoinRepository
import com.patrykkosieradzki.feature.home.domain.model.AllTimeHigh
import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.feature.home.domain.model.Link
import com.patrykkosieradzki.feature.home.domain.model.Supply

internal class CoinApiRepository(
    private val coinRankingService: CoinRankingService
) : CoinRepository {
    override suspend fun getCoins(pageNumber: Int, loadSize: Int): List<Coin> {
        return coinRankingService.getCoins(loadSize, pageNumber * loadSize).data.coins.toDomain()
    }
    override suspend fun getCoinDetails(id: String): Coin {
        return coinRankingService.getCoinDetails(id).data.coin.toDomain()
    }
}

@JvmName("toDomainCoinResponse")
internal fun List<CoinResponse>.toDomain() = map { it.toDomain() }
internal fun CoinResponse.toDomain() = Coin(
    uuid = uuid,
    symbol = symbol,
    name = name,
    color = color,
    iconUrl = iconUrl,
    marketCap = marketCap,
    price = price,
    listedAt = listedAt,
    tier = tier,
    change = change,
    rank = rank,
    sparkline = sparkline,
    lowVolume = lowVolume,
    coinrankingURL = coinrankingURL,
    the24HVolume = the24HVolume,
    btcPrice = btcPrice,
    description = description,
    websiteURL = websiteURL,
    links = links.toDomain(),
    supply = supply?.toDomain(),
    numberOfMarkets = numberOfMarkets,
    numberOfExchanges = numberOfExchanges,
    priceAt = priceAt,
    allTimeHigh = allTimeHigh?.toDomain()
)

internal fun List<LinkResponse>.toDomain() = map { it.toDomain() }
internal fun LinkResponse.toDomain() = Link(name, type, url)

internal fun SupplyResponse.toDomain() = Supply(confirmed, total, circulating)

internal fun AllTimeHighResponse.toDomain() = AllTimeHigh(price, timestamp)