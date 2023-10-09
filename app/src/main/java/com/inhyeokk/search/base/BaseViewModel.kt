package com.inhyeokk.search.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val jobScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun launch(block: suspend () -> Unit) {
        jobScope.launch { block() }
    }
}
