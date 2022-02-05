package com.github.rkddlsgur983.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.rkddlsgur983.search.data.cafe.model.Document
import com.github.rkddlsgur983.search.extension.fromHtml
import com.github.rkddlsgur983.search.ui.cafe.SearchCafeViewModel
import com.github.rkddlsgur983.search.ui.theme.SearchTheme

class MainActivity : ComponentActivity() {
    private val searchCafeViewModel: SearchCafeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SearchCafeList(searchCafeViewModel = searchCafeViewModel)
                }
            }
        }
    }
}

@Composable
fun SearchCafeList(
    searchCafeViewModel: SearchCafeViewModel,
    modifier: Modifier = Modifier
) {
    val documents by searchCafeViewModel.documentListStateFlow.collectAsState()
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SearchTheme {

    }
}
