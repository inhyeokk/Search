package com.rkddlsgur983.search.presentation.ui.cafe.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rkddlsgur983.search.presentation.ui.cafe.model.Document

class SearchCafeAdapter : ListAdapter<Document, SearchCafeViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCafeViewHolder {
        return SearchCafeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SearchCafeViewHolder, position: Int) {
        holder.bind(getItem(position))
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