package com.rkddlsgur983.search.presentation.compose.cafe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rkddlsgur983.search.data.remote.cafe.SearchCafeRepositoryImpl
import com.rkddlsgur983.search.data.remote.cafe.SortType
import com.rkddlsgur983.search.domain.SearchCafeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

class SearchCafeViewModel : ViewModel() {
    private val searchCafeRepository: SearchCafeRepository = SearchCafeRepositoryImpl()

    private val _queryStateFlow = MutableStateFlow("")
    val queryStateFlow: StateFlow<String> = _queryStateFlow
    private val sortTypeStateFlow = MutableStateFlow(SortType.ACCURACY)
    private val onSearchClick = MutableStateFlow(_queryStateFlow.value)

    @ExperimentalCoroutinesApi
    val documentListStateFlow = onSearchClick.flatMapLatest { query ->
        searchCafeRepository.searchCafeWithPaging(query, sortTypeStateFlow.value).cachedIn(viewModelScope)
    }

    fun setQuery(query: String) {
        _queryStateFlow.value = query
    }

    fun onSearchClick(query: String) {
        onSearchClick.value = query
    }

}
