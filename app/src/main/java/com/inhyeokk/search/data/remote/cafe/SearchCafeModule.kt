package com.inhyeokk.search.data.remote.cafe

import com.inhyeokk.search.data.remote.api.KakaoSearchServiceFactory
import com.inhyeokk.search.data.remote.api.SearchCafeApi
import com.inhyeokk.search.domain.cafe.SearchCafeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchCafeModule {

    @Provides
    fun provideSearchCafeApi(): SearchCafeApi {
        return KakaoSearchServiceFactory.create(SearchCafeApi::class.java)
    }

    @Provides
    fun provideSearchCafeRepository(searchCafeRepositoryImpl: SearchCafeRepositoryImpl): SearchCafeRepository {
        return searchCafeRepositoryImpl
    }
}
