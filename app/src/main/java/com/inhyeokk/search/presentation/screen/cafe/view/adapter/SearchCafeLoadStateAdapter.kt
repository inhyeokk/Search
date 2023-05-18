package com.inhyeokk.search.presentation.screen.cafe.view.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class SearchCafeLoadStateAdapter(
    private val onRetry: () -> Unit
) : LoadStateAdapter<SearchCafeLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState
    ): SearchCafeLoadStateViewHolder {
        return SearchCafeLoadStateViewHolder.create(parent, loadState, onRetry)
    }

    override fun onBindViewHolder(holder: SearchCafeLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
