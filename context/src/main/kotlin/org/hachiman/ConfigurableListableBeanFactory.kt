package org.hachiman

import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.config.BeanPostProcessor

interface ConfigurableListableBeanFactory : BeanFactory {


    fun addBeanPostProcessor(beanPostProcessor: BeanPostProcessor)

}