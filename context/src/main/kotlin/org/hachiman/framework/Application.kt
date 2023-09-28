package org.hachiman.framework

import org.hachiman.context.annotation.AnnotationConfigApplicationContext
import org.hachiman.context.support.GenericApplicationContext

class Application(private val mainApplicationClass: Class<*>) {

    companion object {
        fun run(clazz: Class<*>): GenericApplicationContext {
            return Application(clazz).run()
        }
    }


    fun run(): GenericApplicationContext {
        // 构建context
        val context: GenericApplicationContext = createApplicationContext()




        // 前置扫描classpath下的文件并注册bean definition
        val loader = ApplicationBeanDefinitionLoader(context, *getAllPackages())
        loader.load()

        context.refresh()

        return context
    }


    private fun createApplicationContext(): GenericApplicationContext {
        return AnnotationConfigApplicationContext()
    }


    private fun getAllPackages(): Array<String> {
        return arrayOf(mainApplicationClass.packageName)
    }

}