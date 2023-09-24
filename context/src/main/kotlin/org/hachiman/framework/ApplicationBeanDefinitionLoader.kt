package org.hachiman.framework

import org.hachiman.beans.factory.definition.BeanDefinitionRegistry
import org.hachiman.context.annotation.ClassPathBeanDefinitionScanner

/**
 * 用于加载项目下的所有bean definition
 * TODO 增加基于注解扫描的实现 和 load的多种入参格式支持
 */
class ApplicationBeanDefinitionLoader(
    registry: BeanDefinitionRegistry,
    private vararg val packagePaths: String
) {

    private val scanner = ClassPathBeanDefinitionScanner(registry)


    fun load() {
        packagePaths.forEach {
            scanner.scan(it)
        }
    }

}