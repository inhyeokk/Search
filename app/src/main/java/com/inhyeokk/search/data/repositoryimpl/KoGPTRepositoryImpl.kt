package com.inhyeokk.search.data.repositoryimpl

import com.inhyeokk.search.data.remote.api.KoGPTApi
import com.inhyeokk.search.data.remote.model.KoGPTResponse
import com.inhyeokk.search.domain.repository.KoGPTRepository
import com.inhyeokk.search.domain.request.KoGPTRequest
import javax.inject.Inject

class KoGPTRepositoryImpl @Inject constructor(
    private val koGPTApi: KoGPTApi
) : KoGPTRepository {
    override suspend fun generateKoGPT(prompt: String): KoGPTResponse {
        return koGPTApi.generateKoGPT(KoGPTRequest(prompt = prompt, maxTokens = 120, n = 2))
    }
}
