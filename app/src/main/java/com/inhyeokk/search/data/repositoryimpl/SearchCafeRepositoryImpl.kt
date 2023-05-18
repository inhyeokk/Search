package com.inhyeokk.search.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inhyeokk.search.data.remote.api.SearchCafeApi
import com.inhyeokk.search.data.remote.source.SearchCafePagingSource
import com.inhyeokk.search.domain.request.SortType
import com.inhyeokk.search.data.remote.model.DocumentDTO
import com.inhyeokk.search.domain.repository.SearchCafeRepository
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
