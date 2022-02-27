package com.patrykkosieradzki.cryptobag.home

import androidx.lifecycle.ViewModel
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiState
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManager
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(),
    SimpleUiStateManager by SimpleUiStateManagerImpl(initialState = SimpleUiState.Loading) {

}