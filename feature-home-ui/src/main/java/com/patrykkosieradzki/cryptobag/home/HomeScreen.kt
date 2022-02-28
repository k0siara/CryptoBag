package com.patrykkosieradzki.cryptobag.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.patrykkosieradzki.composer.composables.SimpleUiStateView
import com.patrykkosieradzki.cryptobag.common.ui.compose.R
import com.patrykkosieradzki.cryptobag.common.ui.compose.theme.price
import com.patrykkosieradzki.cryptobag.common.ui.imageloading.CryptoBagImage
import com.patrykkosieradzki.cryptobag.utils.toNullableString
import com.patrykkosieradzki.feature.home.domain.model.Coin
import java.math.RoundingMode

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel
) {
    val coins = viewModel.coins.collectAsLazyPagingItems()

    SimpleUiStateView(viewModel) {
        HomeScreenContent(
            onSearchClicked = {},
            coins = coins,
            onCoinClicked = viewModel::onCoinClicked
        )
    }
}

@Composable
internal fun HomeScreenContent(
    onSearchClicked: () -> Unit,
    coins: LazyPagingItems<Coin>,
    onCoinClicked: (String?) -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 30.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All Coins",
                        style = MaterialTheme.typography.h1
                    )

                    IconButton(
                        onClick = onSearchClicked
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = R.drawable.ic_search_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) {
        LazyColumn {
            items(coins) { coin ->
                coin?.let {
                    CoinListItem(coin = coin, onClick = { onCoinClicked(coin.uuid) })
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
                Text(
                    text = coin.name.toNullableString(),
                    style = MaterialTheme.typography.h4

                )
                Text(
                    text = coin.symbol.toNullableString()
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = coin.getFormattedPrice(),
                style = MaterialTheme.typography.price
            )

            Row(
                modifier = Modifier.padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                coin.change?.let {
                    Icon(
                        painter = painterResource(
                            id = when {
                                it.contains("-") -> R.drawable.ic_arrow_down_red_24
                                else -> R.drawable.ic_arrow_up_green_24
                            }
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = coin.getChangeInPercentage()
                    )
                }
            }
        }
    }
}
