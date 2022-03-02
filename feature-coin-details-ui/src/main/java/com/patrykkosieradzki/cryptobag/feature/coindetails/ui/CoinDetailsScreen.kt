package com.patrykkosieradzki.cryptobag.feature.coindetails.ui

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.patrykkosieradzki.composer.composables.SimpleUiStateView
import com.patrykkosieradzki.composer.utils.asLifecycleAwareState
import com.patrykkosieradzki.cryptobag.common.ui.compose.R
import com.patrykkosieradzki.cryptobag.common.ui.compose.theme.green
import com.patrykkosieradzki.cryptobag.common.ui.compose.theme.lightGrey
import com.patrykkosieradzki.cryptobag.common.ui.imageloading.CryptoBagImage
import com.patrykkosieradzki.cryptobag.utils.toNullableString

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel
) {
    val coin by viewModel.coin.asLifecycleAwareState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .padding(vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = viewModel::onBackArrowClicked) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                                contentDescription = null
                            )
                        }

                        CryptoBagImage(
                            modifier = Modifier.size(30.dp),
                            url = coin?.iconUrl
                        )

                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = coin?.symbol.toNullableString(),
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 22.sp
                            )
                        )

                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "#${coin?.rank.toString()}",
                            style = MaterialTheme.typography.body1
                        )
                    }

                    IconButton(
                        modifier = Modifier.background(lightGrey, shape = CircleShape),
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_search_24),
                            contentDescription = null,
                            tint = green
                        )
                    }
                }
            }
        }
    ) {
        SimpleUiStateView(viewModel) {
            coin?.let {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        CryptoBagImage(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(top = 20.dp),
                            url = it.iconUrl
                        )

                        Text(
                            modifier = Modifier.padding(top = 20.dp),
                            text = it.symbol.toNullableString()
                        )
                        Text(text = it.name.toNullableString())

                        AndroidView(
                            modifier = Modifier.padding(top = 20.dp),
                            factory = { context ->
                                TextView(context).apply {
                                    text = HtmlCompat.fromHtml(
                                        it.description.toNullableString(),
                                        HtmlCompat.FROM_HTML_MODE_LEGACY
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}