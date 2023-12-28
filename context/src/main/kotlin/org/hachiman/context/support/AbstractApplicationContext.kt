package org.hachiman.context.support

import org.hachiman.beans.factory.config.BeanPostProcessor
import org.hachiman.beans.factory.config.ConfigurableListableBeanFactory
import org.hachiman.context.ConfigurableApplicationContext

abstract class AbstractApplicationContext : ConfigurableApplicationContext {


    override fun getBean(beanName: String): Any {
        return this.getBeanFactory().getBean(beanName)
    }

    override fun <T> getBean(beanName: String, beanClass: Class<T>): T {
        return this.getBeanFactory().getBean(beanName, beanClass)
    }

    override fun <T> getBean(beanClass: Class<T>): T {
        return this.getBeanFactory().getBean(beanClass)
    }

    override fun getBeanDefinitionCount(): Int {
        return getBeanFactory().getBeanDefinitionCount()
    }

    override fun getBeanNamesForType(type: Class<*>): Array<String> {
        return getBeanFactory().getBeanNamesForType(type)
    }

    override fun <T> getBeansOfType(type: Class<T>): List<T> {
        return getBeanFactory().getBeansOfType(type)
    }

    /**
     * 刷新整个容器
     */
    override fun refresh() {
        // 构建内部的bean factory
        val beanFactory: ConfigurableListableBeanFactory = obtainFreshBeanFactory()

        // 预加载
        prepareBeanFactory(beanFactory)

        // 添加beanPostProcessor用于在bean创建时拦截
        registerBeanPostProcessors(beanFactory)

    }

    private fun obtainFreshBeanFactory(): ConfigurableListableBeanFactory {
        return getBeanFactory()
    }

    private fun prepareBeanFactory(beanFactory: ConfigurableListableBeanFactory) {

        // 加载Aware接口后置处理
        beanFactory.addBeanPostProcessor(ApplicationContextAwareProcessor(this))
    }


    /**
     * 获取容器中所有BeanPostProcessor的子类,并添加到list中
     */
    private fun registerBeanPostProcessors(beanFactory: ConfigurableListableBeanFactory) {
        beanFactory.getBeansOfType(BeanPostProcessor::class.java).forEach {
            beanFactory.addBeanPostProcessor(it)
        }
    }

    override fun containsBeanDefinition(beanName: String): Boolean {
        return getBeanFactory().containsBeanDefinition(beanName)
    }

    /**
     * get beanFactory
     */
    abstract fun getBeanFactory(): ConfigurableListableBeanFactory

}