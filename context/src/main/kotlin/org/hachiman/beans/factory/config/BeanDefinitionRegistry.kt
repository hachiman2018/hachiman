package org.hachiman.beans.factory.config

interface BeanDefinitionRegistry {


    /**
     * register bean definition
     */
    fun registerBeanDefinition(beanName: String, beanDefinition: BeanDefinition)


    /**
     * get bean definition
     */
    fun getBeanDefinition(beanName: String): BeanDefinition


    /**
     * check bean definition exist
     */
    fun containsBeanDefinition(beanName: String): Boolean

}