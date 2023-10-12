package beans.component.aware

import beans.component.Name
import org.hachiman.context.ApplicationContext
import org.hachiman.context.ApplicationContextAware
import org.hachiman.stereotype.Component

@Component
class MyApplicationContextAware : ApplicationContextAware {

    private var context: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }


    fun getName() {
        context?.getBean("name", Name::class.java)?.printName()
    }
}