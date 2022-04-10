package com.rkddlsgur983.search.data.remote.cafe

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rkddlsgur983.search.data.remote.api.KakaoSearchServiceFactory
import com.rkddlsgur983.search.data.remote.api.SearchCafeApi
import com.rkddlsgur983.search.data.remote.cafe.model.Document
import com.rkddlsgur983.search.data.remote.cafe.model.SearchCafeResponse
import com.rkddlsgur983.search.domain.SearchCafeRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.await

class SearchCafeRepositoryImpl : SearchCafeRepository {

    private val searchCafeApi = KakaoSearchServiceFactory.create(SearchCafeApi::class.java)

    override suspend fun searchCafe(
        query: String,
        sortType: SortType
    ): SearchCafeResponse {
        return searchCafeApi.searchCafeWithCall(query, sortType.value).await()
    }

    override fun searchCafeWithPaging(
        query: String,
        sortType: SortType
    ): Flow<PagingData<Document>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchCafePagingSource(query, sortType) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}
