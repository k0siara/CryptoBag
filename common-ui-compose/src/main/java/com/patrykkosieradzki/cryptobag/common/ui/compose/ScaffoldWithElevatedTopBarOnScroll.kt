package com.patrykkosieradzki.cryptobag.common.ui.compose

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScaffoldWithElevatedTopBarOnListScroll(
    lazyListState: LazyListState = rememberLazyListState(),
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues, LazyListState) -> Unit
) {
    val topBarElevation by animateTopBarElevation(
        notElevatedConditions = lazyListState.firstVisibleItemIndex == 0
                && lazyListState.firstVisibleItemScrollOffset == 0
    )

    Scaffold(
        topBar = {
            Surface(
                elevation = topBarElevation
            ) {
                topBar()
            }
        },
        bottomBar = bottomBar,
        content = {
            content(it, lazyListState)
        }
    )
}

@Composable
fun animateTopBarElevation(
    notElevatedConditions: Boolean,
    animationSpec: AnimationSpec<Dp> = snap(),
    finishedListener: ((Dp) -> Unit)? = null
): State<Dp> {
    return animateValueAsState(
        targetValue = if (notElevatedConditions) {
            0.dp
        } else {
            4.dp
        },
        Dp.VectorConverter,
        animationSpec,
        finishedListener = finishedListener
    )
}