package com.mzet.uda

import java.util.*

class UDApp {
    val scanner = Scanner(System.`in`)
    var onExit = false

    fun run() {
        println(WELCOME)
        println(helpString)

        onExit = false

        while (!onExit) {
            if (!scanner.hasNext()) continue
            val args = parseArgs()

            if (args.isEmpty() || args[0].isBlank()) continue

            val msg = when (args[0]) {
                EXIT -> exit()
                KEY -> changeKey(args.getOrNull(1))
                DICTIONARY -> dict(args.getOrNull(1), args.getOrNull(2))
                RANDOM -> random()
                else -> INCORRECT_INPUT
            }

            println(msg)
        }
    }

    fun parseArgs(): List<String> = scanner.nextLine()
        ?.trim()
        ?.lowercase()
        ?.split(Regex("\\s+"))
        ?: emptyList()

    fun exit(): String {
        onExit = true
        return ON_EXIT
    }

    fun changeKey(key: String?): String {
        if (key == null) {
            return NO_KEY
        }

        rapidapiKey = key
        return ON_KEY_SET
    }

    fun dict(word: String?, indexString: String?): String {

        if (rapidapiKey == null) {
            return NO_KEY
        }

        var definitionIndex: Int? = null
        var wordInfo: Wordlist.WordInfo? = null

        if (word == null) {
            return INCORRECT_INPUT
        }

        if (indexString != null) {
            try {
                definitionIndex = indexString.toInt() - 1
            } catch (e: NumberFormatException) {
                return INCORRECT_INPUT
            }
        }

        val wordList = getWordFromDict(word).list

        definitionIndex?.let { i ->
            wordInfo = wordList.getOrElse(i) { wordList.first() }
        } ?: run {
            if (wordList.size > 1) {
                return "Found ${wordList.size} definitions"
            } else {
                wordInfo = wordList.firstOrNull()
            }
        }

        return wordInfo?.let {
             formatDefinition(word, it.definition, it.example, it.author)
        } ?: NO_DEFINITION
    }

    fun random(): String {
        return getRandomWordFromDict()?.let { wordInfo ->
            formatDefinition(
                wordInfo.word,
                wordInfo.definition,
                wordInfo.example,
                wordInfo.author
            )
        } ?: CONNECTION_LOST
    }

    fun formatDefinition(word: String, definition: String, example: String, author: String): String =
        "$word\n" +
                "$definition\n" +
                "Example: $example\n" +
                "Author: $author"
}
