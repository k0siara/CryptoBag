package com.patrykkosieradzki.cryptobag.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.patrykkosieradzki.composer.composables.SimpleUiStateView
import com.patrykkosieradzki.cryptobag.common.ui.imageloading.CryptoBagImage
import com.patrykkosieradzki.cryptobag.utils.toNullableString
import com.patrykkosieradzki.feature.home.domain.model.Coin
import java.math.RoundingMode

@Composable
internal fun HomeScreen(viewModel: HomeViewModel) {
    val coins = viewModel.coins.collectAsLazyPagingItems()

    SimpleUiStateView(viewModel) {
        LazyColumn {
            items(coins) { coin ->
                coin?.let {
                    CoinListItem(coin = coin, onClick = { viewModel.onCoinClicked(coin.uuid) })
                }
            }
        }
    }
}

@Composable
internal fun CoinListItem(
    coin: Coin,
    onClick: (String?) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = { onClick.invoke(coin.uuid) }
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CryptoBagImage(
                modifier = Modifier.size(50.dp),
                url = coin.iconUrl
            )
            Column {
                Text(text = coin.symbol.toNullableString())
                Text(text = coin.name.toNullableString())
            }
        }
        Text(text = "$ ${coin.price.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)}")
    }
}