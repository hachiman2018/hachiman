package org.hachiman.beans.factory.support

import org.hachiman.ConfigurableListableBeanFactory
import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.config.BeanDefinition

class DefaultListableBeanFactory : AbstractAutowireCapableBeanFactory(), ConfigurableListableBeanFactory {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()


    override fun <T> getBean(beanClass: Class<*>): T {
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


    fun printBeanDefinition() {
        beanDefinitionMap.forEach { (t, u) ->
            println("beanName is $t,  beanClass is ${u.beanClass}")
        }
    }
}