package com.mzet.uda

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`);
    var command: String? = null
    println(WELCOME)
    printHelp()

    while (command != EXIT) {
        if (!scanner.hasNext()) continue
        val args = scanner.nextLine()
            ?.trim()
            ?.lowercase()
            ?.split(Regex("\\s+"))
            ?: emptyList()

        if (args.isEmpty() || args[0].isBlank()) {
            continue
        }

        when (args[0]) {
            EXIT -> {
                command = EXIT
                println(ON_EXIT)
            }
            DICTIONARY -> {
                var definitionIndex: Int? = null
                var wordInfo: Wordlist.WordInfo? = null

                if (args.size < 2) {
                    println(INCORRECT_INPUT)
                    continue
                }
                if (args.size >= 3) {
                    try {
                        definitionIndex = args[2].toInt() - 1
                    } catch (e: NumberFormatException) {
                        println(INCORRECT_INPUT)
                        continue
                    }
                }

                val wordList = getWordFromDict(args[1]).list

                if (definitionIndex == null) {
                    if (wordList.size > 1) {
                        println("Found ${wordList.size} definitions")
                    } else {
                        wordInfo = wordList[0]
                    }
                } else {
                    wordInfo = wordList.getOrElse(definitionIndex) { wordList.first() }
                }

                wordInfo?.let {
                    println("${wordInfo.definition}\n")
                    println("Example: ${wordInfo.example}\n")
                    println("Author: ${wordInfo.author}")
                }
            }
            else -> {
                println(INCORRECT_INPUT)
            }
        }
    }
}