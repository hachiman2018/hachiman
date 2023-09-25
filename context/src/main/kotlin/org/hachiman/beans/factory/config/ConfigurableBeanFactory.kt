package org.hachiman.beans.factory.config

import org.hachiman.beans.factory.BeanFactory

interface ConfigurableBeanFactory : BeanFactory {


    fun addBeanPostProcessor(beanPostProcessor: BeanPostProcessor)

}