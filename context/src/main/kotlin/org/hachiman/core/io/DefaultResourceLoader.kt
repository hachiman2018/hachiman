package org.hachiman.core.io

import org.hachiman.core.util.ResourceUtils
import java.net.URL

class DefaultResourceLoader : ResourceLoader {

    /**
     * Must support classpath pseudo-URLs, e.g. "classpath:test.dat".
     * Must support classpath pseudo-files, e.g. "file:C:/test.dat".
     * Should support relative file paths, e.g. "WEB-INF/test.dat
     */
    override fun getResource(location: String): Resource {
        if (location.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
            return ClassPathResource(location.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length))
        }
        if (location.startsWith("/")) {
            return FileSystemResource(location)
        }

        val url = URL(location)
        return if (ResourceUtils.isFileUrl(url)) {
            FileUrlResource(url)
        } else {
            UrlResource(url)
        }
    }
}