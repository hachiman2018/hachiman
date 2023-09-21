package org.hachiman.util

import org.hachiman.stereotype.Component


fun scanPackage(packageName: String, clazz: Class<out Annotation>): List<Class<*>> {
    // load path resources
    val iterator = ClassLoaderUtil.getClassLoader().getResources(packageName).iterator()

    while (iterator.hasNext()) {
        val next = iterator.next()
        println(next.protocol)
        println(next.file)
    }



    return listOf()
}