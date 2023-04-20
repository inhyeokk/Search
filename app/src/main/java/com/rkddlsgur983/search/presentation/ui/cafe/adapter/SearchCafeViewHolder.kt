package com.rkddlsgur983.search.presentation.ui.cafe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkddlsgur983.search.databinding.ItemSearchCafeBinding
import com.rkddlsgur983.search.presentation.cafe.model.Document

class SearchCafeViewHolder private constructor(
    private val binding: ItemSearchCafeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(document: Document) {
        binding.document = document
    }

    companion object {
        fun create(parent: ViewGroup): SearchCafeViewHolder {
            val binding = ItemSearchCafeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return SearchCafeViewHolder(binding)
        }
    }
}
