package com.github.rkddlsgur983.search.data.cafe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.rkddlsgur983.search.data.cafe.model.Document
import kotlinx.coroutines.flow.Flow

class SearchCafeRepository {

    fun searchCafe(query: String, sortType: SortType): Flow<PagingData<Document>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchCafePagingSource(query, sortType) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}
