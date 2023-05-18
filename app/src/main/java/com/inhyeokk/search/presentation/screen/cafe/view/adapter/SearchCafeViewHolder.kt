package com.inhyeokk.search.presentation.screen.cafe.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inhyeokk.search.databinding.ItemSearchCafeBinding
import com.inhyeokk.search.presentation.model.Document

class SearchCafeViewHolder private constructor(
    private val binding: ItemSearchCafeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(document: Document) {
        binding.tvTitle.text = document.title
        binding.tvContents.text = document.contents
        binding.tvCafeName.text = document.cafeName
        binding.tvDate.text = document.datetime
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
