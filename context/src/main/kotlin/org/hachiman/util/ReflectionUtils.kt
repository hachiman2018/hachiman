package org.hachiman.util

import java.lang.reflect.Constructor
import java.lang.reflect.Modifier

object ReflectionUtils {

    fun makeAccessible(ctor: Constructor<*>) {
        if ((Modifier.isPublic(ctor.modifiers)) || (!Modifier.isPublic(ctor.declaringClass.modifiers) && !ctor.canAccess(
                null
            ))
        ) {
            ctor.isAccessible = true
        }
    }
}