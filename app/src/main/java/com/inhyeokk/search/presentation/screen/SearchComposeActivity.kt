package com.inhyeokk.search.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.inhyeokk.search.presentation.screen.cafe.compose.SearchCafe
import com.inhyeokk.search.presentation.screen.theme.SearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchApp()
        }
    }
}

@Composable
fun SearchApp() {
    SearchTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            SearchCafe()
        }
    }
}
