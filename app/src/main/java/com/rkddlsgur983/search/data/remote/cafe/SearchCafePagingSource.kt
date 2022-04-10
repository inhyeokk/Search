package com.rkddlsgur983.search.data.remote.cafe

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rkddlsgur983.search.data.remote.api.KakaoSearchServiceFactory
import com.rkddlsgur983.search.data.remote.api.SearchCafeApi
import com.rkddlsgur983.search.data.remote.cafe.model.Document
import retrofit2.HttpException
import java.io.IOException

class SearchCafePagingSource(
    private val query: String,
    private val sortType: SortType
) : PagingSource<Int, Document>() {

    private val searchCafeApi = KakaoSearchServiceFactory.create(SearchCafeApi::class.java)

    override fun getRefreshKey(state: PagingState<Int, Document>): Int? {
        return state.anchorPosition?.let { it + 1 }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        if (query.isNotEmpty()) {
            val page = params.key ?: STARTING_PAGE_INDEX
            return try {
                val response = searchCafeApi.searchCafe(query, sortType.value, page, params.loadSize)
                val documents = response.documents
                LoadResult.Page(
                    data = documents,
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (documents.isEmpty()) null else page + 1
                )
            } catch (exception: IOException) {
                LoadResult.Error(exception)
            } catch (exception: HttpException) {
                LoadResult.Error(exception)
            }
        } else {
            return LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}
