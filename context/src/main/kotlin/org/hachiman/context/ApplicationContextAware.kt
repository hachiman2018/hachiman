package org.hachiman.context

import org.hachiman.beans.factory.Aware

interface ApplicationContextAware : Aware {


    fun setApplicationContext(applicationContext: ApplicationContext)
}