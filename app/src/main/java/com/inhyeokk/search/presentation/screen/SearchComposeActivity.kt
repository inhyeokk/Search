package com.inhyeokk.search.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.inhyeokk.search.presentation.screen.cafe.compose.SearchCafeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SearchCafeScreen()
            }
        }
    }
}
