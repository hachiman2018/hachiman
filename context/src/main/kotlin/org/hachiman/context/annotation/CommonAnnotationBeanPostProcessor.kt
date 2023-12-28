package org.hachiman.context.annotation

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.hachiman.beans.factory.config.BeanPostProcessor
import java.lang.reflect.Method


/**
 * {@link  javax.annotation.PostConstruct}和{@link javax.annotation.PreDestroy}
 * @author admin
 */
class CommonAnnotationBeanPostProcessor : BeanPostProcessor {


    private val initAnnotationType: Class<out Annotation>

    private val destroyAnnotationType: Class<out Annotation>

    init {
        initAnnotationType = PostConstruct::class.java
        destroyAnnotationType = PreDestroy::class.java
    }


    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        val initMethods: List<Method> = findInitMethods(bean)

        invokeInitMethods(bean, initMethods)

        return bean
    }

    private fun findInitMethods(bean: Any): List<Method> {
        val initMethods: MutableList<Method> = mutableListOf()
        // find init method witt present @PostConstruct annotation
        with(initMethods) {
            bean::class.java.declaredMethods.filter {
                it.isAnnotationPresent(initAnnotationType)
            }.forEach {
                add(it)
            }
        }
        return initMethods.toList()
    }

    private fun invokeInitMethods(bean: Any, initMethods: List<Method>) {
        initMethods.forEach {
            it.invoke(bean)
        }
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        return bean
    }
}