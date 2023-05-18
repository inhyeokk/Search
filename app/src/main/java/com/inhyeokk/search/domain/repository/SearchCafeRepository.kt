package com.inhyeokk.search.domain.repository

import androidx.paging.PagingData
import com.inhyeokk.search.domain.request.SortType
import com.inhyeokk.search.data.remote.model.DocumentDTO
import kotlinx.coroutines.flow.Flow

interface SearchCafeRepository {
    fun searchCafe(query: String, sortType: SortType): Flow<PagingData<DocumentDTO>>
}
