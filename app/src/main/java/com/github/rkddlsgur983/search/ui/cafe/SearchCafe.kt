package com.github.rkddlsgur983.search.ui.cafe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.rkddlsgur983.search.data.cafe.model.Document
import com.github.rkddlsgur983.search.extension.fromHtml

@Composable
fun SearchCafe(searchCafeViewModel: SearchCafeViewModel = viewModel()) {
    val query by searchCafeViewModel.queryStateFlow.collectAsState()
    val documents by searchCafeViewModel.documentListStateFlow.collectAsState()
    SearchCafeList(
        query = query,
        onQueryChange = { searchCafeViewModel.setQuery(it) },
        onSearchClicked = { searchCafeViewModel.loadInit() },
        documents = documents
    )
}

@Composable
fun SearchCafeList(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
    documents: List<Document>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.fillMaxWidth()) {
        item { AppBar(query, onQueryChange, onSearchClicked) }
        items(documents) { document ->
            SearchCafeListItem(
                document = document,
                modifier = Modifier.fillMaxWidth()
            )
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
    onQueryChange: (String) -> Unit,
    onSearchClicked: () -> Unit
) {
    TopAppBar(elevation = 0.dp) {
        // TODO hint
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
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
