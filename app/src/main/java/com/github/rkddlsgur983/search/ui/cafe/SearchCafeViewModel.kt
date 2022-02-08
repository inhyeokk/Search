package com.github.rkddlsgur983.search.ui.cafe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.github.rkddlsgur983.search.data.cafe.SearchCafeRepository
import com.github.rkddlsgur983.search.data.cafe.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

class SearchCafeViewModel : ViewModel() {
    private val searchCafeRepository = SearchCafeRepository()

    private val _queryStateFlow = MutableStateFlow("")
    val queryStateFlow: StateFlow<String> = _queryStateFlow
    private val sortTypeStateFlow = MutableStateFlow(SortType.ACCURACY)
    private val onSearchClick = MutableStateFlow(_queryStateFlow.value)

    @ExperimentalCoroutinesApi
    val documentListStateFlow = onSearchClick.flatMapLatest { query ->
        searchCafeRepository.searchCafe(query, sortTypeStateFlow.value).cachedIn(viewModelScope)
    }

    fun setQuery(query: String) {
        _queryStateFlow.value = query
    }

    fun onSearchClick(query: String) {
        onSearchClick.value = query
    }

}
