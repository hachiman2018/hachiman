package org.hachiman.context.support

import org.hachiman.beans.factory.config.BeanPostProcessor
import org.hachiman.context.ApplicationContextAware
import org.hachiman.context.ConfigurableApplicationContext

class ApplicationContextAwareProcessor(private val applicationContext: ConfigurableApplicationContext) :
    BeanPostProcessor {


    // TODO 扩展更多的Aware接口
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        if (bean !is ApplicationContextAware) {
            return bean
        }

        callAware(bean)

        return bean
    }

    private fun callAware(bean: ApplicationContextAware) {
        bean.setApplicationContext(applicationContext)
    }

}