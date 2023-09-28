package org.hachiman.beans.factory

/**
 * 提供从容器中查询bean的多种方法
 * TODO 提供更多的查询方法
 * @author ljp
 */
interface ListableBeanFactory : BeanFactory {

    /**
     * 判断是否存在beanDefinition
     */
    fun containsBeanDefinition(beanName: String): Boolean

    /**
     * 获取所有的beanDefinition数量
     */
    fun getBeanDefinitionCount(): Int

    /**
     * 基于class获取所有的bean name
     */
    fun getBeanNamesForType(type: Class<*>): Array<String>


    /**
     * 基于给定的类查询所有的bean
     */
    fun <T> getBeansOfType(type: Class<T>): List<T>


}