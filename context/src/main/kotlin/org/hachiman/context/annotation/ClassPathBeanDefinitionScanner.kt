package org.hachiman.context.annotation

import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.extend.lowerFirst
import org.hachiman.stereotype.Component
import org.hachiman.util.ClassScanner

class ClassPathBeanDefinitionScanner(private val registry: BeanDefinitionRegistry) {

    fun scan(vararg packageNames: String) {
        packageNames.forEach { packageName ->
            val candidates =
                ClassScanner(packageName) { it.isAnnotationPresent(Component::class.java) }.scanPackage().map {
                    BeanDefinition(it)
                }
            // set bean scope
            candidates.forEach {
                val scopeValue = resolveBeanScope(it)
                if (scopeValue.isNotBlank()) {
                    it.scope = scopeValue
                }
                val lazy = resolveLazy(it)
                it.lazy = lazy

                // TODO 可考虑增加开关, 用于是否允许map覆盖
                registry.registerBeanDefinition(determineBeanName(it.beanClass), it)
            }

        }
    }


    /**
     * 如果component指定则使用指定,否则使用类名首字母小写
     */
    private fun determineBeanName(clazz: Class<*>): String {
        val component = clazz.getAnnotation(Component::class.java)
        return component.value.ifBlank { clazz.simpleName.lowerFirst() }
    }


    /**
     * resolve bean scope
     */
    private fun resolveBeanScope(beanDefinition: BeanDefinition): String {
        val scope = beanDefinition.beanClass.getAnnotation(Scope::class.java)
        return scope?.value ?: ""
    }

    /**
     * resolve bean lazy or not
     */
    private fun resolveLazy(beanDefinition: BeanDefinition): Boolean {
        val lazy = beanDefinition.beanClass.getAnnotation(Lazy::class.java)
        return lazy?.value ?: false
    }
}