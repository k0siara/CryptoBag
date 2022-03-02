package com.patrykkosieradzki.cryptobag.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.patrykkosieradzki.composer.composables.SimpleUiStateView
import com.patrykkosieradzki.cryptobag.common.ui.compose.LazyColumnWithLockedScrolling
import com.patrykkosieradzki.cryptobag.common.ui.compose.R
import com.patrykkosieradzki.cryptobag.common.ui.compose.ScaffoldWithElevatedTopBarOnListScroll
import com.patrykkosieradzki.cryptobag.common.ui.compose.shimmer.Shimmer
import com.patrykkosieradzki.cryptobag.common.ui.compose.shimmer.ShimmerBounds
import com.patrykkosieradzki.cryptobag.common.ui.compose.shimmer.rememberShimmer
import com.patrykkosieradzki.cryptobag.common.ui.compose.shimmer.shimmer
import com.patrykkosieradzki.cryptobag.common.ui.compose.theme.*
import com.patrykkosieradzki.cryptobag.common.ui.imageloading.CryptoBagImage
import com.patrykkosieradzki.cryptobag.utils.toNullableString
import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.composer.utils.asLifecycleAwareState

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel
) {
    val refreshLoadState by viewModel.refreshLoadState.asLifecycleAwareState()
    val coins = viewModel.coins.collectAsLazyPagingItems()

    SimpleUiStateView(viewModel) {
        HomeScreenContent(
            onSearchClicked = viewModel::onSearchClicked,
            coins = coins,
            refreshLoadState = refreshLoadState,
            onRefreshLoadStateChange = viewModel::onRefreshLoadStateChange,
            onCoinClicked = viewModel::onCoinClicked
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun HomeScreenContent(
    onSearchClicked: () -> Unit,
    coins: LazyPagingItems<Coin>,
    refreshLoadState: LoadState,
    onRefreshLoadStateChange: (LoadState) -> Unit,
    onCoinClicked: (String?) -> Unit
) {
    var isCollapsed by remember { mutableStateOf(false) }
    var lastFirstVisibleItemIndex by remember { mutableStateOf(0) }
    val animateVerticalTopBarPadding by animateDpAsState(
        targetValue = when {
            isCollapsed -> 10.dp
            else -> 30.dp
        }
    )
    val animatedFontSize by animateIntAsState(
        targetValue = when {
            isCollapsed -> 22
            else -> 28
        }
    )
    val animatedIconSize by animateDpAsState(
        targetValue = when {
            isCollapsed -> 24.dp
            else -> 28.dp
        }
    )

    ScaffoldWithElevatedTopBarOnListScroll(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = animateVerticalTopBarPadding)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All Coins",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = animatedFontSize.sp
                        )
                    )

                    IconButton(
                        modifier = Modifier.background(lightGrey, shape = CircleShape),
                        onClick = onSearchClicked
                    ) {
                        Icon(
                            modifier = Modifier.size(animatedIconSize),
                            painter = painterResource(id = R.drawable.ic_search_24),
                            contentDescription = null,
                            tint = green
                        )
                    }
                }
            }
        }
    ) { paddingValues, lazyListState ->
        LaunchedEffect(lazyListState.firstVisibleItemIndex) {
            if (lazyListState.firstVisibleItemIndex > lastFirstVisibleItemIndex) {
                isCollapsed = true
            } else if (lazyListState.firstVisibleItemIndex < lastFirstVisibleItemIndex) {
                isCollapsed = false
            }

            lastFirstVisibleItemIndex = lazyListState.firstVisibleItemIndex
        }

        CoinPagingList(
            paddingValues = paddingValues,
            lazyListState = lazyListState,
            coins = coins,
            refreshLoadState = refreshLoadState,
            onRefreshLoadStateChange = onRefreshLoadStateChange,
            onCoinClicked = onCoinClicked
        )
    }
}

@Composable
fun CoinPagingList(
    paddingValues: PaddingValues,
    lazyListState: LazyListState,
    coins: LazyPagingItems<Coin>,
    refreshLoadState: LoadState,
    onRefreshLoadStateChange: (LoadState) -> Unit,
    onCoinClicked: (String?) -> Unit
) {
    var hasStartedLoading by remember { mutableStateOf(false) }
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    LaunchedEffect(coins.loadState.refresh) {
        if (!hasStartedLoading && coins.loadState.refresh == LoadState.Loading) {
            hasStartedLoading = true
        } else if (coins.loadState.refresh != refreshLoadState && hasStartedLoading) {
            onRefreshLoadStateChange.invoke(coins.loadState.refresh)
        }
    }

    Crossfade(
        targetState = refreshLoadState::class
    ) {
        when (it) {
            LoadState.Loading::class -> {
                LazyColumnWithLockedScrolling {
                    items(20) {
                        ShimmerCoinListItem(shimmer = shimmer)
                    }
                }
            }
            LoadState.NotLoading::class -> {
                LazyColumn(
                    modifier = Modifier.padding(paddingValues),
                    state = lazyListState
                ) {
                    items(coins) { coin ->
                        coin?.let {
                            CoinListItem(
                                coin = coin,
                                onClick = { onCoinClicked(coin.uuid) }
                            )
                        }
                    }
                }
            }
            LoadState.Error::class -> {
                Text(text = "Error, to be implemented...")
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
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CryptoBagImage(
                modifier = Modifier.size(48.dp),
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
                    val isDown = it.contains("-")

                    Icon(
                        painter = painterResource(
                            id = when {
                                isDown -> R.drawable.ic_arrow_down_red_24
                                else -> R.drawable.ic_arrow_up_green_24
                            }
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = coin.getChangeInPercentage(),
                        color = if (isDown) red else green
                    )
                }
            }
        }
    }
}

@Composable
fun ShimmerCoinListItem(
    modifier: Modifier = Modifier,
    shimmer: Shimmer
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .shimmer(shimmer)
                    .background(shimmerGrey)
            )
            Column {
                Box(
                    modifier = modifier
                        .height(18.dp)
                        .width(100.dp)
                        .shimmer(shimmer)
                        .clip(RoundedCornerShape(4.dp))
                        .background(shimmerGrey)
                )
                Box(
                    modifier = modifier
                        .height(18.dp)
                        .width(50.dp)
                        .shimmer(shimmer)
                        .clip(RoundedCornerShape(4.dp))
                        .background(shimmerGrey)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = modifier
                    .height(18.dp)
                    .width(100.dp)
                    .shimmer(shimmer)
                    .clip(RoundedCornerShape(4.dp))
                    .background(shimmerGrey)
            )

            Row(
                modifier = Modifier.padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .height(18.dp)
                        .width(50.dp)
                        .shimmer(shimmer)
                        .clip(RoundedCornerShape(4.dp))
                        .background(shimmerGrey)
                )
            }
        }
    }
}