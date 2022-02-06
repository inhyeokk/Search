package com.github.rkddlsgur983.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.rkddlsgur983.search.ui.cafe.SearchCafeList
import com.github.rkddlsgur983.search.ui.cafe.SearchCafeViewModel
import com.github.rkddlsgur983.search.ui.theme.SearchTheme

@Composable
fun SearchApp(searchCafeViewModel: SearchCafeViewModel) {
    SearchTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            val documents by searchCafeViewModel.documentListStateFlow.collectAsState()
            SearchCafeList(documents)
        }
    }
}
