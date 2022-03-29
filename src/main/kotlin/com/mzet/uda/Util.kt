package com.mzet.uda

// Cmd
const val KEY = "key"
const val DICTIONARY = "dict"
const val RANDOM = "random"
const val HELP = "help"
const val EXIT = "exit"

// Msg
const val WELCOME = "Don't forget specify Rapidapi key first" +
        "\n" +
        "\n" +
        "Type one of the following command:"
const val INCORRECT_INPUT = "Incorrect input"
const val NO_KEY = "Specify rapidapi key"
const val NO_DEFINITION = "No definitions found"
const val ON_KEY_SET = "Rapidapi key is set"
const val ON_EXIT = "Shut down service"
const val CONNECTION_LOST = "Something went wrong"

val commandHelp = mapOf(
    KEY to "Specifiy your own key from https://rapidapi.com/",
    DICTIONARY to "Get specified [word] from dictionary (you can specify [index] of definition if there are several)",
    RANDOM to "Get random word definition",
    HELP to "Get available command list",
    EXIT to "Shut down app"
)

val helpString = commandHelp.entries.joinToString("\n") { "\t" + it.key + " – " + it.value }
