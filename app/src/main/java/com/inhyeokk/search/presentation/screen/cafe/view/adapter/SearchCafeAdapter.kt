package com.inhyeokk.search.presentation.screen.cafe.view.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.inhyeokk.search.presentation.model.Document

class SearchCafeAdapter : PagingDataAdapter<Document, SearchCafeViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCafeViewHolder {
        return SearchCafeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SearchCafeViewHolder, position: Int) {
        // PagingConfig.enablePlaceholders = true 인 경우 getItem(position): Nullable
        // PagingConfig.enablePlaceholders = false 인 경우 getItem(position): NonNull
        holder.bind(getItem(position)!!)
    }

    private object DiffCallback : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }
    }

}
