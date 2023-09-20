package org.hachiman.beans.factory.definition

interface BeanDefinitionRegistry {


    fun registerBeanDefinition(beanName: String, beanDefinition: BeanDefinition)


    fun getBeanDefinition(beanName: String): BeanDefinition

}