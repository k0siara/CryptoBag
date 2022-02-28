package com.patrykkosieradzki.cryptobag.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiState
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManager
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManagerImpl
import com.patrykkosieradzki.composer.toast.ToastManager
import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.feature.home.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val toastManager: ToastManager
) : ViewModel(),
    SimpleUiStateManager by SimpleUiStateManagerImpl(initialState = SimpleUiState.Success) {

    val coins: Flow<PagingData<Coin>> by lazy {
        Pager(
            PagingConfig(
                enablePlaceholders = true,
                prefetchDistance = 20,
                initialLoadSize = 100,
                pageSize = 100
            ),
            pagingSourceFactory = { CoinsPagingSource(getCoinsUseCase, 0) }
        ).flow.cachedIn(viewModelScope)
    }

    fun onSearchClicked() {
        toastManager.showToast("Not implemented yet")
    }

    fun onCoinClicked(uuid: String?) {
        // TODO
    }
}