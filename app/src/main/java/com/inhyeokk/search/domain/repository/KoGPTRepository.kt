package com.inhyeokk.search.domain.repository

import com.inhyeokk.search.data.remote.model.KoGPTResponse

interface KoGPTRepository {
    suspend fun generateKoGPT(prompt: String): KoGPTResponse
}
