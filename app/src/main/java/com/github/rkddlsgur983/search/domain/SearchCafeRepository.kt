package com.github.rkddlsgur983.search.domain

import androidx.paging.PagingData
import com.github.rkddlsgur983.search.data.remote.cafe.SortType
import com.github.rkddlsgur983.search.data.remote.cafe.model.Document
import kotlinx.coroutines.flow.Flow

interface SearchCafeRepository {
    fun searchCafe(query: String, sortType: SortType): Flow<PagingData<Document>>
}
