package com.sdk.foddy.util

import org.jsoup.Jsoup

fun String.toCleanString(): String {
    val jsoup = Jsoup.parse(this)
    return jsoup.text()
}
