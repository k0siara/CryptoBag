package com.patrykkosieradzki.cryptobag.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiState
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManager
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManagerImpl
import com.patrykkosieradzki.composer.extensions.launchWithExceptionHandler
import com.patrykkosieradzki.feature.home.data.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel(),
    SimpleUiStateManager by SimpleUiStateManagerImpl(initialState = SimpleUiState.Loading) {

    fun initialize() {
        launchWithExceptionHandler {
            coinRepository.getCoins()
            updateUiStateToSuccess()
        }
    }
}