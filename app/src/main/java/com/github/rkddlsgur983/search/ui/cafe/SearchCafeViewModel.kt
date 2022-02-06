package com.github.rkddlsgur983.search.ui.cafe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rkddlsgur983.search.data.cafe.SearchCafeRepository
import com.github.rkddlsgur983.search.data.cafe.SortType
import com.github.rkddlsgur983.search.data.cafe.model.Document
import com.github.rkddlsgur983.search.extension.join
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchCafeViewModel : ViewModel() {
    private val searchCafeRepository = SearchCafeRepository()

    private val _queryStateFlow = MutableStateFlow("")
    val queryStateFlow: StateFlow<String> = _queryStateFlow
    private val sortTypeStateFlow = MutableStateFlow(SortType.ACCURACY)
    private val sizeStateFlow = MutableStateFlow(10)
    private var page = 1

    private val _documentListStateFlow = MutableStateFlow<List<Document>>(emptyList())
    val documentListStateFlow: StateFlow<List<Document>> = _documentListStateFlow

    init {
        loadInit(query = "아이유")
    }

    fun setQuery(query: String) {
        _queryStateFlow.value = query
    }

    fun loadInit(
        query: String = _queryStateFlow.value,
        sortType: SortType = sortTypeStateFlow.value,
        size: Int = sizeStateFlow.value
    ) {
        _queryStateFlow.value = query
        sortTypeStateFlow.value = sortType
        sizeStateFlow.value = size
        page = 1
        if (query.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                val searchCafeResponse = searchCafeRepository.searchCafe(query, sortType, page++, size)
                _documentListStateFlow.value = searchCafeResponse.documents
            }
        } else {
            _documentListStateFlow.value = emptyList()
        }
    }

    fun loadMore() {
        val query = _queryStateFlow.value
        val sortType = sortTypeStateFlow.value
        val size = sizeStateFlow.value
        viewModelScope.launch(Dispatchers.IO) {
            val searchCafeResponse = searchCafeRepository.searchCafe(query, sortType, page++, size)
            _documentListStateFlow.apply { value = value.join(searchCafeResponse.documents) }
        }
    }
}
