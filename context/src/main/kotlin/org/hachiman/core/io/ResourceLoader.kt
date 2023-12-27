package org.hachiman.core.io

interface ResourceLoader {


    fun getResource(location: String): Resource
}