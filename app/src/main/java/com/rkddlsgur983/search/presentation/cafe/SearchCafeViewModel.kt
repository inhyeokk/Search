package com.rkddlsgur983.search.presentation.cafe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rkddlsgur983.search.data.remote.cafe.SortType
import com.rkddlsgur983.search.domain.cafe.SearchCafeRepository
import com.rkddlsgur983.search.presentation.cafe.model.Document
import com.rkddlsgur983.search.util.extension.fromHtml
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchCafeViewModel @Inject constructor(
    private val searchCafeRepository: SearchCafeRepository
) : ViewModel() {

    val queryStateFlow = MutableStateFlow("")
    private val sortTypeStateFlow = MutableStateFlow(SortType.ACCURACY)
    private val onSearchClick = MutableStateFlow(queryStateFlow.value)

    @ExperimentalCoroutinesApi
    val documentListStateFlow: Flow<PagingData<Document>> =
        onSearchClick.flatMapLatest(this::searchCafe)

    private fun searchCafe(query: String): Flow<PagingData<Document>> {
        return searchCafeRepository.searchCafe(query, sortTypeStateFlow.value)
            .cachedIn(viewModelScope) // 뷰모델 내에서 값을 캐싱 ex) activity 에서 할당한 flow 를 LiveData 처럼 fragment 에서도 참조할 수 있다.
            .map { pagingData ->
                pagingData.map {
                    Document(
                        id = System.currentTimeMillis(),
                        title = it.title.fromHtml(),
                        contents = it.contents.fromHtml(),
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
