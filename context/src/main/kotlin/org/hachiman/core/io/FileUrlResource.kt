package org.hachiman.core.io

import java.net.URL

class FileUrlResource : UrlResource {

    constructor(url: URL) : super(url) {

    }

    constructor(path: String) : super(path) {

    }

}