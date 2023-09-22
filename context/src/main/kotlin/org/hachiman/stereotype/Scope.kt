package org.hachiman.stereotype


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Scope(val value: String = "singleton")
