package com.inhyeokk.search.di

import com.inhyeokk.search.data.repositoryimpl.KoGPTRepositoryImpl
import com.inhyeokk.search.data.repositoryimpl.SearchCafeRepositoryImpl
import com.inhyeokk.search.domain.repository.KoGPTRepository
import com.inhyeokk.search.domain.repository.SearchCafeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindSearchCafeRepository(impl: SearchCafeRepositoryImpl): SearchCafeRepository

    @ViewModelScoped
    @Binds
    abstract fun bindKoGPTRepository(impl: KoGPTRepositoryImpl): KoGPTRepository
}
