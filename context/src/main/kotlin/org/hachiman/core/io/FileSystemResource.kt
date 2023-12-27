package org.hachiman.core.io

import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

class FileSystemResource(path: String) : Resource {
    private val path: String
    private val filePath: Path
    private val file: File

    init {
        this.path = path.substring(1);
        this.file = File(this.path)
        this.filePath = this.file.toPath()
    }

    override fun getInputStream(): InputStream {
        return Files.newInputStream(filePath)
    }
}