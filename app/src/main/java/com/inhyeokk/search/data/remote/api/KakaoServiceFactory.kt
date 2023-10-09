package com.inhyeokk.search.data.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val REST_API_KEY = "11c910a4fdc84019077e83af26a733f8"

object KakaoServiceFactory {

    fun <T> create(apiClass: Class<T>, baseUrl: BaseUrl = BaseUrl.DEFAULT): T {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor)
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl.value)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)
    }

    private object HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder().apply {
                addHeader("Content-Type", "application/json")
                addHeader("Authorization", "KakaoAK $REST_API_KEY")
            }.build()
            return chain.proceed(request)
        }
    }
}
