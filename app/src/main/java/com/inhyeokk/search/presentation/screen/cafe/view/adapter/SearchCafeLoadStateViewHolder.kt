package com.inhyeokk.search.presentation.screen.cafe.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.inhyeokk.search.databinding.ItemSearchCafeLoadStateBinding

class SearchCafeLoadStateViewHolder private constructor(
    private val binding: ItemSearchCafeLoadStateBinding, loadState: LoadState, onRetry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { onRetry() }
        bind(loadState)
    }

    fun bind(loadState: LoadState) {
        binding.pbLoadState.isVisible = loadState is LoadState.Loading
        binding.btnRetry.isVisible = loadState is LoadState.Error
        binding.tvErrorMessage.isVisible = loadState is LoadState.Error
        if (loadState is LoadState.Error) {
            binding.tvErrorMessage.text = loadState.error.localizedMessage
        }
    }

    companion object {
        fun create(
            parent: ViewGroup, loadState: LoadState, onRetry: () -> Unit
        ): SearchCafeLoadStateViewHolder {
            val binding = ItemSearchCafeLoadStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return SearchCafeLoadStateViewHolder(binding, loadState, onRetry)
        }
    }
}
