package org.hachiman.beans.factory.config

import org.hachiman.beans.factory.BeanFactory

/**
 * TODO 提供了自动装配能力
 */
interface AutowireCapableBeanFactory : BeanFactory {
    fun createBean(beanName: String, beanDefinition: BeanDefinition): Any

}