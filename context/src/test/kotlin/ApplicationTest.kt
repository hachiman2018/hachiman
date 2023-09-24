import beans.address.Address
import org.hachiman.framework.Application
import org.junit.jupiter.api.Test

class ApplicationTest {


    @Test
    fun testInit() {

        val context = Application.run(ApplicationTest::class.java)

        val bean = context.getBean("myAddress")
        if (bean is Address) {
            bean.printAddress()
        }
    }
}