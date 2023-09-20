package org.hachiman.beans.factory

interface BeanFactory {

    /**
     * get bean by bean name
     */
    fun getBean(name: String): Any

    /**
     * gen bean by name and type
     */
    fun <T> getBean(name: String, beanClass: Class<*>): T

    /**
     * get bean by type
     */
    fun <T> getBean(beanClass: Class<*>): T


}