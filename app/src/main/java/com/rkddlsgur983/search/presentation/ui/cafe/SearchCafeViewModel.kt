package com.rkddlsgur983.search.presentation.ui.cafe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkddlsgur983.search.data.remote.cafe.SortType
import com.rkddlsgur983.search.domain.SearchCafeRepository
import com.rkddlsgur983.search.presentation.ui.cafe.model.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCafeViewModel @Inject constructor(
    private val searchCafeRepository: SearchCafeRepository
) : ViewModel() {

    val queryLiveData = MutableLiveData<String>()
    private val _sortTypeLiveDate = MutableLiveData(SortType.ACCURACY)

    private val _documentListLiveData = MutableLiveData<List<Document>>()
    val documentListLiveData: LiveData<List<Document>> = _documentListLiveData

    fun searchCafe() {
        val query = queryLiveData.value ?: ""
        if (query.isEmpty()) return
        val sortType = _sortTypeLiveDate.value ?: SortType.ACCURACY
        viewModelScope.launch {
            try {
                val response = searchCafeRepository.searchCafe(query, sortType)
                _documentListLiveData.value = response.documents.map {
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
            } catch (e: Exception) {
                Log.e("searchCafe", e.toString())
            }
        }
    }
}
