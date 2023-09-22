package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.util.makeAccessible

/**
 * abstract BeanFactory
 *
 * provide singleton bean cache
 */
abstract class AbstractBeanFactory : BeanFactory, BeanDefinitionRegistry {

    // cache singleton bean
    private val singletonBeanCache = mutableMapOf<String, Any>()


    private fun getCacheSingletonBean(beanName: String): Any? = singletonBeanCache[beanName]


    override fun getBean(beanName: String): Any {
        return doGetBean(beanName)
    }

    override fun <T> getBean(beanName: String, beanClass: Class<*>): T {
        return doGetBean(beanName) as? T ?: throw BeansException("bean cast exception")
    }


    private fun doGetBean(beanName: String): Any {
        return getCacheSingletonBean(beanName) ?: createBean(beanName, getBeanDefinition(beanName))
    }


    /**
     * 创建bean  TODO 后续可下放到子类
     */
    private fun createBean(beanName: String, beanDefinition: BeanDefinition): Any {
        if (beanDefinition.beanClass.isInterface) {
            throw BeansException("${beanDefinition.beanClass} is interface, create fail")
        }

        if (beanDefinition.isSingleton()) {
            val singletonBean = doCreate(beanDefinition)
            singletonBeanCache[beanName] = singletonBean
            return singletonBean
        } else if (beanDefinition.isProperty()) {
            return doCreate(beanDefinition)
        }
        throw BeansException("bean scope not support to create")
    }


    /**
     * TODO 现只支持无参构造函数,后续支持有参
     */
    private fun doCreate(beanDefinition: BeanDefinition): Any {
        val noArgConstructor = beanDefinition.beanClass.getDeclaredConstructor()
        makeAccessible(noArgConstructor)
        return noArgConstructor.newInstance()

    }

}