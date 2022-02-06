package com.github.rkddlsgur983.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.github.rkddlsgur983.search.ui.cafe.SearchCafe
import com.github.rkddlsgur983.search.ui.theme.SearchTheme

@Composable
fun SearchApp() {
    SearchTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            SearchCafe()
        }
    }
}
