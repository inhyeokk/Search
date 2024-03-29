package com.inhyeokk.search.extension

import android.text.Html

fun String.fromHtml(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}
