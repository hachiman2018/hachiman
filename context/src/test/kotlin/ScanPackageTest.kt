import org.hachiman.beans.factory.support.DefaultListableBeanFactory
import org.hachiman.context.annotation.ClassPathBeanDefinitionScanner
import org.junit.jupiter.api.Test

class ScanPackageTest {


    @Test
    fun main() {

//        println(ClassLoader.getSystemClassLoader())
//        println(Thread.currentThread().contextClassLoader)
//        println(ScanPackageTest::class.java.classLoader)

        val beanFactory = DefaultListableBeanFactory()
        val scan = ClassPathBeanDefinitionScanner(beanFactory)
        scan.scan("beans")
        beanFactory.printBeanDefinition()

    }


}