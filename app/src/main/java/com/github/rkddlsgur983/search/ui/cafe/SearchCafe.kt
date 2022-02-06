package com.github.rkddlsgur983.search.ui.cafe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.rkddlsgur983.search.data.cafe.model.Document
import com.github.rkddlsgur983.search.extension.fromHtml

@Composable
fun SearchCafeList(
    documents: List<Document>,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        LazyColumn(Modifier.fillMaxWidth()) {
            items(documents) { document ->
                SearchCafeListItem(
                    document = document,
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
