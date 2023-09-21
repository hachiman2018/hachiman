package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.util.ReflectionUtils

class DefaultListableBeanFactory : BeanFactory, BeanDefinitionRegistry {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()


    /**
     * 通过beanName获取beanDefinition, 并通过反射创建对象
     */
    override fun getBean(name: String): Any {
        val beanDefinition = getBeanDefinition(name)
        return createBean(beanDefinition)
    }


    private fun createBean(beanDefinition: BeanDefinition): Any {
        val noArgConstructor = beanDefinition.beanClass.getDeclaredConstructor()
        ReflectionUtils.makeAccessible(noArgConstructor)
        return noArgConstructor.newInstance()
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