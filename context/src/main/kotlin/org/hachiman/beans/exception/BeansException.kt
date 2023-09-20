package org.hachiman.beans.exception

import java.lang.RuntimeException

class BeansException : RuntimeException {
    constructor(message: String) : super(message)

    constructor(message: String, e: Throwable) : super(message, e)
}