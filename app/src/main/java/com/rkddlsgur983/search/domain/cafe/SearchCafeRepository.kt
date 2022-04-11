package com.rkddlsgur983.search.domain.cafe

import androidx.paging.PagingData
import com.rkddlsgur983.search.data.remote.cafe.SortType
import com.rkddlsgur983.search.data.remote.cafe.model.DocumentDTO
import com.rkddlsgur983.search.data.remote.cafe.model.SearchCafeResponse
import kotlinx.coroutines.flow.Flow

interface SearchCafeRepository {
    suspend fun searchCafe(query: String, sortType: SortType): SearchCafeResponse
    fun searchCafeWithPaging(query: String, sortType: SortType): Flow<PagingData<DocumentDTO>>
}
