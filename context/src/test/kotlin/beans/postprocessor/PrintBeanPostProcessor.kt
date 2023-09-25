package beans.postprocessor

import org.hachiman.beans.factory.config.BeanPostProcessor
import org.hachiman.stereotype.Component

@Component
class PrintBeanPostProcessor : BeanPostProcessor {
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        if (beanName == "user") {
            println("user is before Initialization ")
        }
        return super.postProcessBeforeInitialization(bean, beanName)
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (beanName == "user") {
            println("user is after Initialization ")
        }
        return super.postProcessAfterInitialization(bean, beanName)
    }
}