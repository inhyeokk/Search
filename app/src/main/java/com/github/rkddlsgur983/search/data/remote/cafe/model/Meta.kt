package com.github.rkddlsgur983.search.data.remote.cafe.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)
