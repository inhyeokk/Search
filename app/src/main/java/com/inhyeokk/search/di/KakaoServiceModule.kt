package com.inhyeokk.search.di

import com.inhyeokk.search.data.remote.api.BaseUrl
import com.inhyeokk.search.data.remote.api.KakaoServiceFactory
import com.inhyeokk.search.data.remote.api.KoGPTApi
import com.inhyeokk.search.data.remote.api.SearchCafeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object KakaoServiceModule {

    @Provides
    fun provideSearchCafeApi(): SearchCafeApi {
        return KakaoServiceFactory.create(SearchCafeApi::class.java)
    }

    @Provides
    fun provideKoGPTApi(): KoGPTApi {
        return KakaoServiceFactory.create(KoGPTApi::class.java, BaseUrl.BRAIN)
    }

}
