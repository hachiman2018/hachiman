package org.hachiman.context.annotation

import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.extend.lowerFirst
import org.hachiman.stereotype.Component
import org.hachiman.util.ClassScanner

class ClassPathBeanDefinitionScanner(private val registry: BeanDefinitionRegistry) {

    fun scan(vararg packageNames: String) {
        packageNames.forEach { packageName ->
            ClassScanner(packageName) { it.isAnnotationPresent(Component::class.java) }.scanPackage().forEach {
                registry.registerBeanDefinition(getBeanName(it), BeanDefinition(it))
            }
        }
    }

    /**
     * 如果component指定则使用指定,否则使用类名首字母小写
     */
    private fun getBeanName(clazz: Class<*>): String {
        val component = clazz.getAnnotation(Component::class.java)
        return component.value.ifBlank { clazz.simpleName.lowerFirst() };
    }
}