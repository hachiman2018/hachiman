package org.hachiman.context.annotation

import org.hachiman.beans.factory.config.BeanDefinition
import org.hachiman.beans.factory.config.BeanDefinitionRegistry
import org.hachiman.core.util.ClassUtils

/**
 * common beanPostProcessor
 */
object AnnotationConfigUtils {

    /**
     * The bean name of the internally managed JSR-250 annotation processor.
     */
    private const val COMMON_ANNOTATION_PROCESSOR_BEAN_NAME = "org.springframework.context.annotation.internalCommonAnnotationProcessor"

    private val jsr250Present: Boolean

    init {
        val classLoader = this::class.java.classLoader
        jsr250Present = ClassUtils.isPresent("javax.annotation.Resource", classLoader)
    }


    fun registerAnnotationConfigProcessors(registry: BeanDefinitionRegistry) {

        // check for JSR-250 support, and if present add the CommonAnnotationBeanPostProcessor.
        // handler @PostConstruct and @PreDestroy methods on any bean
        if (jsr250Present && !registry.containsBeanDefinition(COMMON_ANNOTATION_PROCESSOR_BEAN_NAME)) {
            registry.registerBeanDefinition(COMMON_ANNOTATION_PROCESSOR_BEAN_NAME, BeanDefinition(CommonAnnotationBeanPostProcessor::class.java))
        }
    }


}