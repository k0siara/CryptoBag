package com.patrykkosieradzki.cryptobag.feature.coindetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patrykkosieradzki.composer.navigation.observeNavigation
import com.patrykkosieradzki.cryptobag.common.ui.compose.extensions.cryptoBagComposeView
import com.patrykkosieradzki.cryptobag.common.ui.compose.extensions.postponeEnterTransitionAndStartOnPreDraw
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {

    private val viewModel: CoinDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return cryptoBagComposeView {
            CoinDetailsScreen(viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransitionAndStartOnPreDraw()
        viewModel.observeNavigation(this)
    }
}
