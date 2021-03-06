package com.patrykkosieradzki.cryptobag.common.ui.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.patrykkosieradzki.cryptobag.common.ui.compose.theme.CryptoBagTheme

fun Fragment.cryptoBagComposeView(
    content: @Composable () -> Unit
): ComposeView {
    return ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            CryptoBagTheme {
                content()
            }
        }
    }
}

fun Fragment.postponeEnterTransitionAndStartOnPreDraw() {
    postponeEnterTransition()
    view?.doOnPreDraw { startPostponedEnterTransition() }
}
