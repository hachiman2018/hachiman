package org.hachiman.context

interface ConfigurableApplicationContext : ApplicationContext {


    fun refresh()
}