package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry

class DefaultBeanFactory : BeanFactory, BeanDefinitionRegistry {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()


    override fun getBean(name: String): Any {
        TODO("Not yet implemented")
    }

    override fun <T> getBean(name: String, beanClass: Class<*>): T {
        TODO("Not yet implemented")
    }

    override fun <T> getBean(beanClass: Class<*>): T {
        TODO("Not yet implemented")
    }

    override fun registerBeanDefinition(beanName: String, beanDefinition: BeanDefinition) {
        beanDefinitionMap[beanName] = beanDefinition;
    }

    override fun getBeanDefinition(beanName: String): BeanDefinition {
        return beanDefinitionMap[beanName] ?: throw BeansException("$beanName is not exist")
    }
}