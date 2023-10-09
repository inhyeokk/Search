package com.inhyeokk.search.data.remote.model

import com.google.gson.annotations.SerializedName

data class KoGPTResponse(
    val id: String,
    val generations: List<Generation>,
    val usage: Usage
)

data class Generation(
    val text: String,
    val tokens: Int
)

data class Usage(
    @SerializedName("prompt_tokens") val promptTokens: Int,
    @SerializedName("generated_tokens") val generatedTokens: Int,
    @SerializedName("total_tokens") val totalTokens: Int
)
