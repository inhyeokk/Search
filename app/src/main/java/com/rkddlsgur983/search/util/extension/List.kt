package com.rkddlsgur983.search.util.extension

fun <T> List<T>.join(itemList: List<T>): List<T> {
    return this.toMutableList().apply { addAll(itemList) }
}
