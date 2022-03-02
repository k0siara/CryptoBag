package com.patrykkosieradzki.cryptobag.common.ui.compose

object CryptoBagNavigation {
    private const val COIN_DETAILS_DEEP_LINK = "cryptobag://coinDetails"

    fun buildCoinDetailsDeepLink(coinId: String): String {
        return "${COIN_DETAILS_DEEP_LINK}?coinId=$coinId"
    }
}