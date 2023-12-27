package org.hachiman.core.io

import java.io.InputStream
import java.net.URL

open class UrlResource : Resource {

    private val url: URL

    constructor(url: URL) {
        this.url = url
    }


    constructor(path: String) {
        this.url = URL(path)
    }


    override fun getInputStream(): InputStream {
        val con = url.openConnection()
        return con.getInputStream()
    }
}