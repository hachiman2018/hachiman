package org.hachiman.util

object ClassLoaderUtil {


    private fun getContextClassLoader(): ClassLoader = Thread.currentThread().contextClassLoader

    /**
     * 获取classload
     */
    fun getClassLoader(): ClassLoader =
        getContextClassLoader() ?: this.getClassLoader() ?: ClassLoader.getSystemClassLoader()


}