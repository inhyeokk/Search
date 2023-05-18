package com.inhyeokk.search.data.remote.model

data class DocumentDTO(
    val title: String,
    val contents: String,
    val url: String,
    val cafename: String,
    val thumbnail: String,
    val datetime: String
)