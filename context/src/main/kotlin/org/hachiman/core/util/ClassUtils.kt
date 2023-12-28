package org.hachiman.core.util

import org.hachiman.util.ClassLoaderUtil

object ClassUtils {


    fun loadClass(className: String, classLoader: ClassLoader?): Class<*> {
        return forName(className, classLoader)
    }

    fun isPresent(className: String, classLoader: ClassLoader?): Boolean {
        // TODO 简单处理 后续增加更多的类型
        forName(className, classLoader)
        return true
    }


    /**
     * 获取classloader
     */
    private fun resolveClassLoader(classLoader: ClassLoader?): ClassLoader {
        return classLoader ?: ClassLoaderUtil.getClassLoader()
    }

    private fun forName(className: String, classLoader: ClassLoader?): Class<*> {
        val resolveClassLoader = resolveClassLoader(classLoader)
        return Class.forName(className, false, resolveClassLoader)
    }
}