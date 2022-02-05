package com.github.rkddlsgur983.search.data.cafe

import com.github.rkddlsgur983.search.api.KakaoSearchServiceFactory
import com.github.rkddlsgur983.search.api.SearchCafeApi
import com.github.rkddlsgur983.search.data.cafe.model.SearchCafeResponse

class SearchCafeRepository {

    private val searchCafeApi = KakaoSearchServiceFactory.create(SearchCafeApi::class.java)

    suspend fun searchCafe(query: String, sortType: SortType, page: Int, size: Int): SearchCafeResponse {
        return searchCafeApi.searchCafe(query, sortType.value, page, size)
    }
}
