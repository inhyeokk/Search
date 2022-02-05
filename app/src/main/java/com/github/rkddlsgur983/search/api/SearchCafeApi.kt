package com.github.rkddlsgur983.search.api

import com.github.rkddlsgur983.search.data.cafe.model.SearchCafeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCafeApi {

    @GET("/v2/search/cafe")
    suspend fun searchCafe(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): SearchCafeResponse

}
