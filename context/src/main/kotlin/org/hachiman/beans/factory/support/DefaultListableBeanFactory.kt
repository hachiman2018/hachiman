package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.util.makeAccessible

class DefaultListableBeanFactory : BeanFactory, BeanDefinitionRegistry {

    private val beanDefinitionMap = mutableMapOf<String, BeanDefinition>()


    /**
     * 通过beanName获取beanDefinition, 并通过反射创建对象
     */
    override fun getBean(beanName: String): Any {
        val beanDefinition = getBeanDefinition(beanName)
        return createBean(beanDefinition)
    }


    private fun createBean(beanDefinition: BeanDefinition): Any {
        val noArgConstructor = beanDefinition.beanClass.getDeclaredConstructor()
        makeAccessible(noArgConstructor)
        return noArgConstructor.newInstance()
    }

    override fun <T> getBean(beanName: String, beanClass: Class<*>): T {
        return getBean(beanName) as? T ?: throw BeansException("bean cast exception")
    }


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