package org.hachiman.beans.factory.definition

interface BeanDefinitionRegistry {


    /**
     * register bean definition
     */
    fun registerBeanDefinition(beanName: String, beanDefinition: BeanDefinition)


    /**
     * get bean definition
     */
    fun getBeanDefinition(beanName: String): BeanDefinition

}