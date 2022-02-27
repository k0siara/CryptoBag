package com.patrykkosieradzki.cryptobag.common.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
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
