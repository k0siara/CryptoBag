package com.patrykkosieradzki.cryptobag.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.patrykkosieradzki.feature.home.domain.model.Coin
import com.patrykkosieradzki.feature.home.domain.usecase.GetCoinsUseCase

class CoinsPagingSource(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val startingPage: Int
) : PagingSource<Int, Coin>() {
    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val pageNumber = params.key ?: startingPage
            val result = getCoinsUseCase.invoke(pageNumber, params.loadSize)
            LoadResult.Page(
                data = result,
                prevKey = null, // Only paging forward.
                nextKey = pageNumber + 1
            )
        } catch (error: Throwable) {
            LoadResult.Error(error)
        }
    }
}