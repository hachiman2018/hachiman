package org.hachiman.core.io

import org.hachiman.util.ClassLoaderUtil
import java.io.FileNotFoundException
import java.io.InputStream

class ClassPathResource(private val path: String, private var classLoader: ClassLoader? = null) : Resource {

    constructor(path: String) : this(path, null) {
        this.classLoader = ClassLoaderUtil.getClassLoader()
    }

    override fun getInputStream(): InputStream {
        return classLoader?.getResourceAsStream(this.path)
                ?: ClassLoaderUtil.getClassLoader().getResourceAsStream(this.path)
                ?: throw FileNotFoundException(getExceptionDescription());
    }

    private fun getExceptionDescription(): String {
        return "class path resource [$path] cannot be opened because it does not exist";
    }


}