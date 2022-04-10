package com.rkddlsgur983.search.data.remote.api

import com.rkddlsgur983.search.data.remote.cafe.model.SearchCafeResponse
import retrofit2.Call
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

    @GET("/v2/search/cafe")
    fun searchCafeWithCall(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<SearchCafeResponse>

}
