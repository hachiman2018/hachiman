package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.config.BeanDefinition
import org.hachiman.beans.factory.config.BeanDefinitionRegistry
import org.hachiman.beans.factory.config.BeanPostProcessor
import org.hachiman.beans.factory.config.ConfigurableBeanFactory

/**
 * abstract BeanFactory
 *
 * provide singleton bean cache
 */
abstract class AbstractBeanFactory : ConfigurableBeanFactory, BeanDefinitionRegistry {

    // cache singleton bean
    private val singletonBeanCache = mutableMapOf<String, Any>()

    // 用于存放BeanPostProcessor 在初始化bean时会链式调用所有的processor
    protected val beanPostProcessors = mutableListOf<BeanPostProcessor>()


    private fun containsCacheSingletonBean(beanName: String) = singletonBeanCache.containsKey(beanName)

    private fun getCacheSingletonBean(beanName: String): Any? = singletonBeanCache[beanName]


    override fun getBean(beanName: String): Any {
        return doGetBean(beanName)
    }

    override fun <T> getBean(beanName: String, beanClass: Class<T>): T {
        return doGetBean(beanName) as? T ?: throw BeansException("bean cast exception")
    }


    private fun doGetBean(beanName: String): Any {
        if (containsCacheSingletonBean(beanName)) {
            return getCacheSingletonBean(beanName) as Any
        }
        val beanDefinition = getBeanDefinition(beanName)
        return if (beanDefinition.isSingleton()) {
            val singletonBean = createBean(beanName, beanDefinition)
            singletonBeanCache[beanName] = singletonBean
            singletonBean
        } else if (beanDefinition.isProperty()) {
            createBean(beanName, beanDefinition)
        } else {
            throw BeansException("bean scope not support to create")
        }
    }


    /**
     * 创建bean
     */
    abstract fun createBean(beanName: String, beanDefinition: BeanDefinition): Any

    override fun addBeanPostProcessor(beanPostProcessor: BeanPostProcessor) {
        //有则覆盖
        beanPostProcessors.remove(beanPostProcessor)
        beanPostProcessors.add(beanPostProcessor)
    }


}