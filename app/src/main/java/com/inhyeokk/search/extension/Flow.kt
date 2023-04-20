package com.inhyeokk.search.extension

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.launchWithLifecycle(
    activity: ComponentActivity,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) = launchWithLifecycle(activity.lifecycle, minActiveState, action)

fun <T> Flow<T>.launchWithLifecycle(
    fragment: Fragment,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) = launchWithLifecycle(fragment.viewLifecycleOwner.lifecycle, minActiveState, action)

private fun <T> Flow<T>.launchWithLifecycle(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State,
    action: suspend (T) -> Unit
) = flowWithLifecycle(lifecycle, minActiveState).onEach(action).launchIn(lifecycle.coroutineScope)
