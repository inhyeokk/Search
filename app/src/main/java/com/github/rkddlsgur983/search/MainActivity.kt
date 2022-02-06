package com.github.rkddlsgur983.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.github.rkddlsgur983.search.ui.cafe.SearchCafeViewModel

class MainActivity : ComponentActivity() {
    private val searchCafeViewModel: SearchCafeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchApp(searchCafeViewModel)
        }
    }
}
