package com.inhyeokk.search.presentation.screen.kogpt

import com.inhyeokk.search.base.BaseViewModel
import com.inhyeokk.search.domain.repository.KoGPTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@HiltViewModel
class KoGPTViewModel @Inject constructor(
    private val koGPTRepository: KoGPTRepository
) : BaseViewModel() {
    val promptFlow = MutableStateFlow("")
    private val generateFlow = MutableStateFlow(promptFlow.value)
    val generationFlow = generateFlow.mapNotNull {
        if (it.isNotEmpty()) {
            koGPTRepository.generateKoGPT(it)
        } else {
            null
        }
    }

    fun setPrompt(prompt: String) = launch {
        promptFlow.emit(prompt)
    }

    fun generateKoGPT(prompt: String = promptFlow.value) = launch {
        generateFlow.emit(prompt)
    }
}
