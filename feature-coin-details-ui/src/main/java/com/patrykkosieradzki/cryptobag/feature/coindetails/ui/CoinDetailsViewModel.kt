package com.patrykkosieradzki.cryptobag.feature.coindetails.ui

import androidx.lifecycle.ViewModel
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiState
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManager
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManagerImpl
import com.patrykkosieradzki.composer.extensions.launchWithExceptionHandler
import com.patrykkosieradzki.composer.navigation.NavigationManager
import com.patrykkosieradzki.composer.navigation.NavigationManagerImpl
import com.patrykkosieradzki.feature.home.domain.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
//    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModel(),
    SimpleUiStateManager by SimpleUiStateManagerImpl(
        initialState = SimpleUiState.Loading
    ),
    NavigationManager by NavigationManagerImpl() {

    val coin = MutableStateFlow<Coin?>(null)

    fun initialize() {
        launchWithExceptionHandler {
//            val coinDetails = getCoinDetailsUseCase.invoke()
            updateUiStateToSuccess {
//                coin.update { Async.Success(coinDetails) }
            }
        }
    }

    fun onBackArrowClicked() {
        navigateBack()
    }
}