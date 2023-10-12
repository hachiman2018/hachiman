package org.hachiman.framework

import org.hachiman.beans.factory.config.BeanDefinitionRegistry
import org.hachiman.context.ApplicationContext
import org.hachiman.context.ConfigurableApplicationContext
import org.hachiman.context.annotation.AnnotationConfigApplicationContext
import org.hachiman.context.support.AbstractApplicationContext
import org.hachiman.context.support.GenericApplicationContext

class Application(private val mainApplicationClass: Class<*>) {

    companion object {
        fun run(clazz: Class<*>): ConfigurableApplicationContext {
            return Application(clazz).run()
        }
    }


    fun run(): ConfigurableApplicationContext {
        // 构建context
        val context: ConfigurableApplicationContext = createApplicationContext()

        prepareContext(context)

        refresh(context);

        return context
    }


    private fun prepareContext(context: ConfigurableApplicationContext) {
        // 前置扫描classpath下的文件并注册bean definition
        val loader = ApplicationBeanDefinitionLoader(getBeanDefinitionRegistry(context), *getAllPackages())
        loader.load()
    }


    private fun getBeanDefinitionRegistry(context: ApplicationContext): BeanDefinitionRegistry {
        if (context is BeanDefinitionRegistry) {
            return context
        }
        if (context is AbstractApplicationContext) {
            return context.getBeanFactory() as BeanDefinitionRegistry
        }
        throw IllegalStateException("Could not locate BeanDefinitionRegistry")
    }

    private fun getAllPackages(): Array<String> {
        return arrayOf(mainApplicationClass.packageName)
    }


    private fun createApplicationContext(): GenericApplicationContext {
        return AnnotationConfigApplicationContext()
    }


    private fun refresh(context: ConfigurableApplicationContext) {
        context.refresh()
    }

}