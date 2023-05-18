package com.inhyeokk.search.presentation.screen.cafe.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.inhyeokk.search.R
import com.inhyeokk.search.presentation.model.Document
import com.inhyeokk.search.presentation.screen.CommonScaffold
import com.inhyeokk.search.presentation.screen.SearchButton
import com.inhyeokk.search.presentation.screen.cafe.SearchCafeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun SearchCafeScreen(searchCafeViewModel: SearchCafeViewModel = viewModel()) {
    val query by searchCafeViewModel.queryStateFlow.collectAsState()
    val documents = searchCafeViewModel.documentListStateFlow.collectAsLazyPagingItems()
    CommonScaffold(
        title = {
            TextField(
                value = query,
                onValueChange = { searchCafeViewModel.setQuery(it) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.SearchCafeActivity_title
                        ), fontSize = 16.sp, color = Color.Gray
                    )
                },
                trailingIcon = {
                    SearchButton {
                        searchCafeViewModel.onClickSearch(query)
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    searchCafeViewModel.onClickSearch(
                        query
                    )
                }),
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
    ) {
        SearchCafeList(it, documents)
        if (documents.itemCount == 0) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.SearchCafeActivity_empty),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun SearchCafeList(
    padding: PaddingValues,
    documents: LazyPagingItems<Document>
) {
    LazyColumn(contentPadding = padding) {
        items(
            count = documents.itemCount,
            key = documents.itemKey(),
            contentType = documents.itemContentType()
        ) { index ->
            documents[index]?.let {
                SearchCafeListItem(
                    document = it, modifier = Modifier.fillMaxWidth()
                )
                Divider()
            }
        }
    }
}

@Composable
fun SearchCafeListItem(
    document: Document,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row {
                Text(text = document.title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = document.cafeName,
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Row {
                Text(text = document.contents, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}
