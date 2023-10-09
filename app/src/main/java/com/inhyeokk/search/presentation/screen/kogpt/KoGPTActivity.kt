package com.inhyeokk.search.presentation.screen.kogpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KoGPTActivity : ComponentActivity() {
    private val viewModel by viewModels<KoGPTViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val prompt by viewModel.promptFlow.collectAsState()
                val generation by viewModel.generationFlow.collectAsState(initial = null)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        TextField(
                            value = prompt, onValueChange = viewModel::setPrompt,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {
                                viewModel.generateKoGPT(prompt)
                            })
                        )
                    }
                ) {
                    LazyColumn(contentPadding = it) {
                        generation?.let {
                            itemsIndexed(
                                items = it.generations,
                                key = { _, item -> item.text },
                            ) { _, item ->
                                Text(text = item.text)
                            }
                        }
                    }
                }
            }
        }
    }
}
