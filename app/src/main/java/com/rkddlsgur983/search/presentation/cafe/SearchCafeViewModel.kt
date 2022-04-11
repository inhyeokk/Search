package com.rkddlsgur983.search.presentation.cafe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rkddlsgur983.search.data.remote.cafe.SortType
import com.rkddlsgur983.search.domain.SearchCafeRepository
import com.rkddlsgur983.search.presentation.cafe.model.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchCafeViewModel @Inject constructor(
    private val searchCafeRepository: SearchCafeRepository
) : ViewModel() {

    val queryStateFlow = MutableStateFlow("")
    private val sortTypeStateFlow = MutableStateFlow(SortType.ACCURACY)
    private val onSearchClick = MutableStateFlow(queryStateFlow.value)

    @ExperimentalCoroutinesApi
    val documentListStateFlow: Flow<PagingData<Document>> = onSearchClick.flatMapLatest { query ->
        searchCafeRepository.searchCafeWithPaging(query, sortTypeStateFlow.value)
            .cachedIn(viewModelScope).map { pagingData ->
                pagingData.map {
                    Document(
                        id = System.currentTimeMillis(),
                        title = it.title,
                        contents = it.contents,
                        url = it.url,
                        cafeName = it.cafename,
                        thumbnail = it.thumbnail,
                        datetime = it.datetime
                    )
                }
            }
    }

    fun setQuery(query: String) {
        queryStateFlow.value = query
    }

    fun onSearchClick(query: String = queryStateFlow.value) {
        onSearchClick.value = query
    }

}
