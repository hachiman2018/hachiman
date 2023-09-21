package org.hachiman.context.annotation

import org.hachiman.beans.factory.definition.BeanDefinition
import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.extend.lowerFirst
import org.hachiman.stereotype.Component
import org.hachiman.util.scanPackage

class ClassPathBeanDefinitionScanner(private val registry: BeanDefinitionRegistry) {


    fun scan(vararg packageNames: String) {
        packageNames.forEach { packageName ->
            scanPackage(packageName, Component::class.java).forEach {
                // 构建bean definition
                registry.registerBeanDefinition(getBeanName(it), BeanDefinition(it))
            }
        }
    }

    /**
     * bean名称首字母小节
     * TODO Component可以修改名称
     */
    private fun getBeanName(it: Class<*>) = it.simpleName.lowerFirst()
}