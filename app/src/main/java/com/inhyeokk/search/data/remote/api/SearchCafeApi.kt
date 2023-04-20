package com.inhyeokk.search.data.remote.api

import com.inhyeokk.search.data.remote.cafe.model.SearchCafeResponse
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
