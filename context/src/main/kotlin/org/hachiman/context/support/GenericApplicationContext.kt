package org.hachiman.context.support

import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.config.BeanDefinition
import org.hachiman.beans.factory.config.BeanDefinitionRegistry
import org.hachiman.beans.factory.config.ConfigurableListableBeanFactory
import org.hachiman.beans.factory.support.DefaultListableBeanFactory

open class GenericApplicationContext : AbstractApplicationContext(), BeanDefinitionRegistry {

    private val beanFactory: DefaultListableBeanFactory = DefaultListableBeanFactory()

    override fun getBeanFactory(): ConfigurableListableBeanFactory {
        return beanFactory
    }

    override fun registerBeanDefinition(beanName: String, beanDefinition: BeanDefinition) {
        beanFactory.registerBeanDefinition(beanName, beanDefinition)
    }

    override fun getBeanDefinition(beanName: String): BeanDefinition {
        return beanFactory.getBeanDefinition(beanName)
    }
}