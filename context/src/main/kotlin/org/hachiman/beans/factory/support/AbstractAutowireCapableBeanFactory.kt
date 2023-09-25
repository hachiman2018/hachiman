package org.hachiman.beans.factory.support

import org.hachiman.beans.exception.BeansException
import org.hachiman.beans.factory.config.BeanDefinition
import org.hachiman.util.makeAccessible

abstract class AbstractAutowireCapableBeanFactory : AbstractBeanFactory() {


    /**
     * 创建bean
     */
    override fun createBean(beanName: String, beanDefinition: BeanDefinition): Any {
        if (beanDefinition.beanClass.isInterface) {
            throw BeansException("${beanDefinition.beanClass} is interface, create fail")
        }

        return doCreateBean(beanName, beanDefinition)
    }


    /**
     * TODO 现只支持无参构造函数,后续支持有参
     */
    private fun doCreateBean(beanName: String, beanDefinition: BeanDefinition): Any {
        val noArgConstructor = beanDefinition.beanClass.getDeclaredConstructor()
        makeAccessible(noArgConstructor)
        val newInstance = noArgConstructor.newInstance()

        return initializeBean(newInstance, beanName)
    }

    private fun initializeBean(bean: Any, beanName: String): Any {

        //执行BeanPostProcessor的前置处理
        val wrappedBean: Any = applyBeanPostProcessorsBeforeInitialization(bean, beanName)


        // todo 执行定义的init方法


        return applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)
    }


    private fun applyBeanPostProcessorsBeforeInitialization(bean: Any, beanName: String): Any {
        var resultBean: Any = bean
        for (beanPostProcessor in beanPostProcessors) {
            resultBean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName) ?: resultBean
        }
        return resultBean
    }

    private fun applyBeanPostProcessorsAfterInitialization(bean: Any, beanName: String): Any {
        var resultBean: Any = bean
        for (beanPostProcessor in beanPostProcessors) {
            resultBean = beanPostProcessor.postProcessAfterInitialization(bean, beanName) ?: resultBean
        }
        return resultBean
    }

}
