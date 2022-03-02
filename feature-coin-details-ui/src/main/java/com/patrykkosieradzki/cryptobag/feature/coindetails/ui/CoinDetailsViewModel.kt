package com.patrykkosieradzki.cryptobag.feature.coindetails.ui

import androidx.lifecycle.ViewModel
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiState
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManager
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManagerImpl
import com.patrykkosieradzki.composer.extensions.launchWithExceptionHandler
import com.patrykkosieradzki.composer.navigation.NavigationManager
import com.patrykkosieradzki.composer.navigation.NavigationManagerImpl
import com.patrykkosieradzki.cryptobag.feature.coindetails.domain.GetCoinDetailsUseCase
import com.patrykkosieradzki.feature.home.domain.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModel(),
    SimpleUiStateManager by SimpleUiStateManagerImpl(
        initialState = SimpleUiState.Loading
    ),
    NavigationManager by NavigationManagerImpl() {

    val coin = MutableStateFlow<Coin?>(null)

    fun initialize(coinId: String) {
        launchWithExceptionHandler {
            val coinDetails = getCoinDetailsUseCase.invoke(coinId)
            updateUiStateToSuccess {
                coin.update { coinDetails }
            }
        }
    }

    fun onBackArrowClicked() {
        navigateBack()
    }
}