package org.hachiman.context.support

import org.hachiman.beans.factory.config.BeanPostProcessor
import org.hachiman.context.ApplicationContext
import org.hachiman.context.ApplicationContextAware

class ApplicationContextAwareProcessor(private val applicationContext: GenericApplicationContext) : BeanPostProcessor {


    // TODO 扩展更多的Aware接口
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        if (bean !is ApplicationContextAware) {
            return bean
        }

        callAware(bean)

        return bean
    }

    private fun callAware(bean: ApplicationContextAware) {
        if (bean is ApplicationContext) {
            bean.setApplicationContext(applicationContext)
        }
    }

}