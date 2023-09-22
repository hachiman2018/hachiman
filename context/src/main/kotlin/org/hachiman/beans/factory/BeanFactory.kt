package org.hachiman.beans.factory

interface BeanFactory {

    /**
     * get bean by bean name
     */
    fun getBean(beanName: String): Any

    /**
     * gen bean by name and type
     */
    fun <T> getBean(beanName: String, beanClass: Class<*>): T

    /**
     * get bean by type
     */
    fun <T> getBean(beanClass: Class<*>): T


}