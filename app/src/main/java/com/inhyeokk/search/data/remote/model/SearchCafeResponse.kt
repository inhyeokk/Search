package com.inhyeokk.search.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchCafeResponse(
    val meta: Meta,
    val documents: List<DocumentDTO>
)

data class Meta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class DocumentDTO(
    val title: String,
    val contents: String,
    val url: String,
    val cafename: String,
    val thumbnail: String,
    val datetime: String
)
