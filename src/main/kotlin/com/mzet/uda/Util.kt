package com.mzet.uda

// Args
const val KEY = "key"
const val DICTIONARY = "dict"
const val EXIT = "exit"

// Msg
const val WELCOME = "Type one of the following command:"
const val INCORRECT_INPUT = "Incorrect input"
const val NO_KEY = "Specify rapidapi key"
const val ON_KEY_SET = "Rapidapi key is set"
const val ON_EXIT = "Shut down service"

val commandHelp = mapOf(
    DICTIONARY to "Get specified word from dictionary",
    EXIT to "Shut down app"
)

fun printHelp() {
    println(commandHelp.entries.joinToString("\n") { "\t" + it.key + " – " + it.value })
}
