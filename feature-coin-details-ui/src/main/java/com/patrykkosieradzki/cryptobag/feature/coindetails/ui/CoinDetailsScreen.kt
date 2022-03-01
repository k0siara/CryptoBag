package com.patrykkosieradzki.cryptobag.feature.coindetails.ui

import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.patrykkosieradzki.composer.composables.SimpleUiStateView
import com.patrykkosieradzki.composer.utils.asLifecycleAwareState
import com.patrykkosieradzki.cryptobag.common.ui.compose.R
import com.patrykkosieradzki.cryptobag.common.ui.imageloading.CryptoBagImage
import com.patrykkosieradzki.cryptobag.utils.toNullableString

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel
) {
    val coin by viewModel.coin.asLifecycleAwareState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Crypto app") },
                navigationIcon = {
                    IconButton(onClick = viewModel::onBackArrowClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
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