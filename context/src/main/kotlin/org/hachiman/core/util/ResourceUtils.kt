package org.hachiman.core.util

import java.net.URL

object ResourceUtils {

    const val CLASSPATH_URL_PREFIX = "classpath:"

    const val URL_PROTOCOL_FILE = "file"


    fun isFileUrl(url: URL): Boolean {
        val protocol = url.protocol
        return protocol == URL_PROTOCOL_FILE
    }
}