package org.hachiman.extend

fun String.lowerFirst(): String =
    if (this.length > 1) {
        val firstChar = this[0]
        Character.toLowerCase(firstChar) + this.substring(1)
    } else
        this
