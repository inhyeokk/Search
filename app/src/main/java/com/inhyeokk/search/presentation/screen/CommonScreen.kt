package com.inhyeokk.search.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScaffold(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White), topBar = {
            CommonTopAppBar(navigationIcon = navigationIcon, title = title)
        }, content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(
    navigationIcon: @Composable () -> Unit = {}, title: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        TopAppBar(
            navigationIcon = navigationIcon,
            title = title,
        )
    }
}

@Composable
fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Image(imageVector = Icons.Default.Close, contentDescription = "닫기")
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Image(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
    }
}

@Composable
fun SearchButton(isEnabled: Boolean = true, onClick: () -> Unit) {
    IconButton(onClick, enabled = isEnabled) {
        Image(imageVector = Icons.Default.Search, contentDescription = "검색")
    }
}

@Composable
fun SearchImage() {
    SearchButton(onClick = {}, isEnabled = false)
}
