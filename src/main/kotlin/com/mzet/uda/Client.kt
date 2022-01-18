package com.mzet.uda

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

val client = OkHttpClient()
val gson = Gson()
var rapidapiKey: String? = null

fun getWordFromDict(word: String): Wordlist {
    val request = Request.Builder()
        .get()
        .url("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=$word")
        .addHeader("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
        .addHeader("x-rapidapi-key", rapidapiKey ?: "")
        .build()

    val response = client.newCall(request).execute().body?.string()
    return gson.fromJson(response, Wordlist::class.java)
}

data class Wordlist(val list: List<WordInfo>) {
    data class WordInfo(
        var word: String = "",
        var definition: String = "",
        var example: String = "",
        var author: String = ""
    )
}