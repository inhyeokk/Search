package com.rkddlsgur983.search.presentation.cafe.model

data class Document(
    val id: Long,
    val title: String,
    val contents: String,
    val url: String,
    val cafeName: String,
    val thumbnail: String,
    val datetime: String
)
