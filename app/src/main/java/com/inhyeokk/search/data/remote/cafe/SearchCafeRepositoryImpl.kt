package com.inhyeokk.search.data.remote.cafe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inhyeokk.search.data.remote.api.SearchCafeApi
import com.inhyeokk.search.data.remote.cafe.model.DocumentDTO
import com.inhyeokk.search.domain.cafe.SearchCafeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCafeRepositoryImpl @Inject constructor(
    private val searchCafeApi: SearchCafeApi
) : SearchCafeRepository {

    override fun searchCafe(
        query: String,
        sortType: SortType
    ): Flow<PagingData<DocumentDTO>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchCafePagingSource(searchCafeApi, query, sortType) }
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}
