package com.rkddlsgur983.search.domain.cafe

import androidx.paging.PagingData
import com.rkddlsgur983.search.data.remote.cafe.SortType
import com.rkddlsgur983.search.data.remote.cafe.model.DocumentDTO
import kotlinx.coroutines.flow.Flow

interface SearchCafeRepository {
    fun searchCafe(query: String, sortType: SortType): Flow<PagingData<DocumentDTO>>
}
