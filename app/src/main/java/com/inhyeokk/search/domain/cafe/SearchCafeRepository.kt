package com.inhyeokk.search.domain.cafe

import androidx.paging.PagingData
import com.inhyeokk.search.data.remote.cafe.SortType
import com.inhyeokk.search.data.remote.cafe.model.DocumentDTO
import kotlinx.coroutines.flow.Flow

interface SearchCafeRepository {
    fun searchCafe(query: String, sortType: SortType): Flow<PagingData<DocumentDTO>>
}
