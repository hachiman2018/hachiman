import beans.Name
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
        scan.scan(ScanPackageTest::class.java.packageName)
        beanFactory.printBeanDefinition()
        val bean = beanFactory.getBean<Name>("name", Name::class.java)
        val bean1 = beanFactory.getBean<Name>(Name::class.java)
        bean.printName()
        bean1.printName()
    }

    @Test
    fun className() {
        val clazz = ScanPackageTest::class.java
        println(clazz.simpleName)
        println(clazz.name)
        println(clazz.canonicalName)
        println(clazz.packageName)
    }


}