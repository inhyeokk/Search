package com.github.rkddlsgur983.search.data.remote.cafe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.rkddlsgur983.search.data.remote.cafe.model.Document
import com.github.rkddlsgur983.search.domain.SearchCafeRepository
import kotlinx.coroutines.flow.Flow

class SearchCafeRepositoryImpl : SearchCafeRepository {

    override fun searchCafe(query: String, sortType: SortType): Flow<PagingData<Document>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchCafePagingSource(query, sortType) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}
