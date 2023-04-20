package com.inhyeokk.search.presentation.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.inhyeokk.search.presentation.compose.cafe.SearchCafe
import com.inhyeokk.search.presentation.compose.theme.SearchTheme

@Composable
fun SearchApp() {
    SearchTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            SearchCafe()
        }
    }
}
