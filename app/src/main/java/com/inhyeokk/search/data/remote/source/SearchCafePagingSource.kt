package com.inhyeokk.search.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.inhyeokk.search.data.remote.api.SearchCafeApi
import com.inhyeokk.search.data.remote.model.DocumentDTO
import com.inhyeokk.search.data.repositoryimpl.SearchCafeRepositoryImpl
import com.inhyeokk.search.domain.request.SortType
import retrofit2.HttpException
import java.io.IOException

class SearchCafePagingSource(
    private val searchCafeApi: SearchCafeApi,
    private val query: String,
    private val sortType: SortType
) : PagingSource<Int, DocumentDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DocumentDTO> {
        if (query.isNotEmpty()) {
            val page = params.key ?: STARTING_PAGE_INDEX
            return try {
                val response = searchCafeApi.searchCafe(
                    query, sortType.value, page, params.loadSize
                )
                val documents = response.documents
                LoadResult.Page(
                    data = documents,
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (documents.isEmpty()) null else page + params.loadSize / SearchCafeRepositoryImpl.PAGE_SIZE
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

    // prevKey == null -> anchorPage is the first page.
    // nextKey == null -> anchorPage is the last page.
    // both prevKey and nextKey null -> anchorPage is the initial page, so just return null.
    override fun getRefreshKey(state: PagingState<Int, DocumentDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}
