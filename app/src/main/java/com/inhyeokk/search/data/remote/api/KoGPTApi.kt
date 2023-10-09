package com.inhyeokk.search.data.remote.api

import com.inhyeokk.search.data.remote.model.KoGPTResponse
import com.inhyeokk.search.domain.request.KoGPTRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface KoGPTApi {

    // https://developers.kakao.com/docs/latest/ko/kogpt/rest-api
    @POST("/v1/inference/kogpt/generation")
    suspend fun generateKoGPT(
        @Body koGPTRequest: KoGPTRequest
    ): KoGPTResponse
}
