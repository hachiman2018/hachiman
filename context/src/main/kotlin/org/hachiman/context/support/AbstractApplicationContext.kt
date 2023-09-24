package org.hachiman.context.support

import org.hachiman.beans.factory.BeanFactory
import org.hachiman.context.ConfigurableApplicationContext

abstract class AbstractApplicationContext : ConfigurableApplicationContext {

    /**
     * get beanFactory
     */
    abstract fun getBeanFactory(): BeanFactory


    override fun getBean(beanName: String): Any {
        return this.getBeanFactory().getBean(beanName)
    }

    override fun <T> getBean(beanName: String, beanClass: Class<*>): T {
        return this.getBeanFactory().getBean(beanName, beanClass)
    }

    override fun <T> getBean(beanClass: Class<*>): T {
        return this.getBeanFactory().getBean(beanClass)
    }
}