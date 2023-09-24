package org.hachiman.context.annotation


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Scope(val value: String = "singleton")
