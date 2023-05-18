package com.inhyeokk.search.presentation.model

data class Document(
    val id: Long,
    val title: String,
    val contents: String,
    val url: String,
    val cafeName: String,
    val thumbnail: String,
    val datetime: String
) {
    companion object {
        fun empty() = Document(
            id = System.currentTimeMillis(),
            title = "",
            contents = "",
            url = "",
            cafeName = "",
            thumbnail = "",
            datetime = ""
        )
    }
}
