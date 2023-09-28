package org.hachiman.beans.factory.config

import org.hachiman.beans.factory.BeanFactory

/**
 * TODO 提供对容器的配置能力
 */
interface ConfigurableBeanFactory : BeanFactory {


    fun addBeanPostProcessor(beanPostProcessor: BeanPostProcessor)

}