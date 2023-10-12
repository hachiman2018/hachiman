package beans

import beans.component.aware.MyApplicationContextAware
import org.hachiman.framework.Application
import org.junit.jupiter.api.Test

class AwareTest {


    @Test
    fun applicationContextTest() {
        val context = Application.run(ApplicationTest::class.java)
        val myContext = context.getBean("myApplicationContextAware", MyApplicationContextAware::class.java)
        myContext.getName()
    }
}