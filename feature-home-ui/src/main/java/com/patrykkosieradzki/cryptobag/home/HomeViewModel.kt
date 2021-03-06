package com.patrykkosieradzki.cryptobag.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import androidx.paging.*
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiState
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManager
import com.patrykkosieradzki.composer.core.state.simple.SimpleUiStateManagerImpl
import com.patrykkosieradzki.composer.navigation.ComposerNavigationCommand
import com.patrykkosieradzki.composer.navigation.NavigationManager
import com.patrykkosieradzki.composer.navigation.NavigationManagerImpl
import com.patrykkosieradzki.composer.toast.ToastManager
import com.patrykkosieradzki.cryptobag.common.ui.compose.CryptoBagNavigation
import com.patrykkosieradzki.cryptobag.common.ui.compose.R
import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.feature.home.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val toastManager: ToastManager
) : ViewModel(),
    SimpleUiStateManager by SimpleUiStateManagerImpl(
        initialState = SimpleUiState.Success
    ), NavigationManager by NavigationManagerImpl() {

    val refreshLoadState = MutableStateFlow<LoadState>(LoadState.Loading)
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

    fun onRefreshLoadStateChange(loadState: LoadState) {
        refreshLoadState.update { loadState }
    }

    fun onSearchClicked() {
        toastManager.showToast("Not implemented yet")
    }

    fun onCoinClicked(coinId: String?) {
        coinId?.let {
            navigate(ComposerNavigationCommand.Custom {
                it.navigate(
                    Uri.parse(CryptoBagNavigation.buildCoinDetailsDeepLink(coinId)),
                    navOptions = navOptions {
                        anim {
                            enter = R.anim.slide_in_right
                            exit = R.anim.slide_out_left
                            popEnter = R.anim.slide_in_left
                            popExit = R.anim.slide_out_right
                        }
                    }
                )
            })
        }
    }
}