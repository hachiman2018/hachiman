package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.config.BeanDefinition
import org.hachiman.beans.factory.config.ConfigurableListableBeanFactory
import java.util.concurrent.ConcurrentHashMap

class DefaultListableBeanFactory : AbstractAutowireCapableBeanFactory(), ConfigurableListableBeanFactory {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()

    private val allBeanNamesByType: Map<Class<*>, Array<String>> = ConcurrentHashMap(64)


    override fun <T> getBean(beanClass: Class<T>): T {
        // 通过beanDefinition遍历获取beanName
        val beanNameList = beanDefinitionMap.filter {
            beanClass.isAssignableFrom(it.value.beanClass)
        }.map { it.key }

        if (beanNameList.size == 1) {
            return getBean(beanNameList[0], beanClass)
        }
        throw BeansException("find multiple bean")

    }


    override fun registerBeanDefinition(beanName: String, beanDefinition: BeanDefinition) {
        beanDefinitionMap[beanName] = beanDefinition
    }

    override fun getBeanDefinition(beanName: String): BeanDefinition {
        return beanDefinitionMap[beanName] ?: throw BeansException("$beanName is not exist")
    }

    override fun containsBeanDefinition(beanName: String): Boolean {
        return beanDefinitionMap.containsKey(beanName)
    }

    override fun getBeanDefinitionCount(): Int {
        return beanDefinitionMap.size
    }

    override fun getBeanNamesForType(type: Class<*>): Array<String> {
        // 尝试从缓存中获取
        return allBeanNamesByType[type] ?: filterByType(type)
    }

    private fun filterByType(type: Class<*>): Array<String> {
        return beanDefinitionMap.filter {
            type.isAssignableFrom(it.value.beanClass)
        }.map { it.key }.toTypedArray()
    }


    override fun <T> getBeansOfType(type: Class<T>): List<T> {
        return beanDefinitionMap.filter {
            type.isAssignableFrom(it.value.beanClass)
        }.map { getBean(it.key, type) }
    }

    fun printBeanDefinition() {
        beanDefinitionMap.forEach { (t, u) ->
            println("beanName is $t,  beanClass is ${u.beanClass}")
        }
    }
}