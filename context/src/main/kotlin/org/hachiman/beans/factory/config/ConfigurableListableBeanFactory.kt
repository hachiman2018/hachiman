package org.hachiman.beans.factory.config

import org.hachiman.beans.factory.BeanFactory
import org.hachiman.beans.factory.ListableBeanFactory
import org.hachiman.beans.factory.config.BeanPostProcessor
import org.hachiman.beans.factory.config.ConfigurableBeanFactory

/**
 * TODO 提供忽略依赖,自动装配判断,冻结bean的定义,枚举所有bean名称的功能
 */
interface ConfigurableListableBeanFactory : ConfigurableBeanFactory, ListableBeanFactory {



}