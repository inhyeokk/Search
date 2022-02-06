package com.github.rkddlsgur983.search.extension

fun <T> List<T>.join(itemList: List<T>): List<T> {
    return this.toMutableList().apply { addAll(itemList) }
}
