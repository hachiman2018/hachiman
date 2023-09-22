package org.hachiman.beans.factory.definition

import org.hachiman.beans.exception.BeansException


/**
 * bean definition
 * TODO 增加更多的参数
 * 比如  参数等
 */
class BeanDefinition(val beanClass: Class<*>) {

    private val SCOPE_SINGLETON = "singleton"

    private val SCOPE_PROPERTY = "property"

    private var singleton = true
    private var property = false

    var scope: String? = null
        set(value) {
            field = value
            singleton = SCOPE_SINGLETON == value
            property = SCOPE_PROPERTY == value
            if (!singleton && !property) {
                throw BeansException("Bean scope mistake")
            }
        }


    fun isSingleton() = singleton


    fun isProperty() = property
}
