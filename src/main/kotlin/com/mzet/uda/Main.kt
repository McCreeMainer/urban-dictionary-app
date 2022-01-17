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
            ?.split(Regex("\\W+"))
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
                if (args.size < 2) println(INCORRECT_INPUT)
                else {
                    val wordInfo = getWordFromDict(args[1]).list[0]
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