fun main() {
    var command: String? = null
    println(WELCOME)
    printHelp()

    while (command != EXIT) {
        val args = readLine()
            ?.trim()
            ?.replace(Regex("""\s+"""), " ")
            ?.lowercase()
            ?.split(" ")
            ?: emptyList()

        if (args.isEmpty()) {
            println(INCORRECT_INPUT)
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