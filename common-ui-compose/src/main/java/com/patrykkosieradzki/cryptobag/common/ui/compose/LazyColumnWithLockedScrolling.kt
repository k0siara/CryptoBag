package com.patrykkosieradzki.cryptobag.common.ui.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.patrykkosieradzki.cryptobag.common.ui.compose.extensions.disableScrolling

@Composable
fun LazyColumnWithLockedScrolling(
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        lazyListState.disableScrolling(scope)
    }

    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        content = content
    )
}