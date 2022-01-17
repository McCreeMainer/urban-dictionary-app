package com.mzet.uda

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

val client = OkHttpClient()
val gson = Gson()

fun getWordFromDict(word: String): Wordlist {
    val request = Request.Builder()
        .get()
        .url("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=$word")
        .addHeader("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "afd8a1edfemsh42e00d1360b74a0p1f7cc4jsnfddab1c1909f")
        .build()

    val response = client.newCall(request).execute().body?.string()
    return gson.fromJson(response, Wordlist::class.java)
}

data class Wordlist(val list: List<WordInfo>) {
    data class WordInfo(
        val definition: String,
        val permalink: String,
        val author: String,
        val example: String,
    )
}