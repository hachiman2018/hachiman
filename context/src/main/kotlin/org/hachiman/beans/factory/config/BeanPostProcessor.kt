package org.hachiman.beans.factory.config

interface BeanPostProcessor {

    /**
     * 在bean执行初始化方法之前执行此方法
     *
     */
    fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        return bean
    }

    /**
     * 在bean执行初始化方法之后执行此方法
     */
    fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        return bean
    }
}