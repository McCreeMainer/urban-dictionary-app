package com.mzet.uda

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.a
import it.skrape.selects.html5.div
import java.lang.Exception

fun getRandomWordFromDict(): Wordlist.WordInfo? {
    try {
        return skrape(HttpFetcher) {
            request {
                url = "https://www.urbandictionary.com/random.php"
            }

            extractIt {
                htmlDocument {
                    it.word = a {
                        withClass = "word"
                        findFirst { text }
                    }
                    it.definition = div {
                        withClass = "meaning"
                        findFirst {
                            text.replace(Regex("""<a.*>"""), "[")
                                .replace(Regex("""</a.*>"""), "]")
                        }
                    }
                    it.example = div {
                        withClass = "example"
                        findFirst {
                            text.replace(Regex("""<a.*>"""), "[")
                                .replace(Regex("""</a.*>"""), "]")
                        }
                    }
                    it.author = div {
                        withClass = "contributor"
                        findFirst {
                            a {
                                findFirst {
                                    text
                                }
                            }
                        }
                    }
                }
            }
        }
    } catch (e: Exception) {
        println(CONNECTION_LOST)
        return null
    }
}