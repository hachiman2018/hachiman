package org.hachiman.core.io

import java.io.InputStream

/**
 * Simple interface for objects that are sources for an InputStream.
 */
interface InputStreamResource {


    fun getInputStream(): InputStream


}