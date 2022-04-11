package com.rkddlsgur983.search.presentation.compose.cafe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.rkddlsgur983.search.presentation.cafe.SearchCafeViewModel
import com.rkddlsgur983.search.presentation.cafe.model.Document
import com.rkddlsgur983.search.util.extension.fromHtml

@Composable
fun SearchCafe(searchCafeViewModel: SearchCafeViewModel = viewModel()) {
    val query by searchCafeViewModel.queryStateFlow.collectAsState()
    val documents = searchCafeViewModel.documentListStateFlow.collectAsLazyPagingItems()
    SearchCafeList(
        query = query,
        onQueryChanged = { searchCafeViewModel.setQuery(it) },
        onSearchClicked = { searchCafeViewModel.onSearchClick(query) },
        documents = documents
    )
}

@Composable
fun SearchCafeList(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    documents: LazyPagingItems<Document>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.fillMaxWidth()) {
        item { AppBar(query, onQueryChanged, onSearchClicked) }
        items(documents) { document ->
            document?.let {
                SearchCafeListItem(
                    document = it,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun SearchCafeListItem(
    document: Document,
    modifier: Modifier
) {
    Column(modifier) {
        Text(text = document.title.fromHtml().toString())
    }
}

@Composable
private fun AppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearchClicked: () -> Unit
) {
    TopAppBar(elevation = 0.dp) {
        // TODO hint
        BasicTextField(
            value = query,
            onValueChange = onQueryChanged,
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        Image(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)
                .clickable { onSearchClicked() }
        )
    }
}
