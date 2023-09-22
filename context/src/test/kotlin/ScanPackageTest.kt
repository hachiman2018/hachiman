import beans.Name
import org.hachiman.beans.factory.support.DefaultListableBeanFactory
import org.hachiman.context.annotation.ClassPathBeanDefinitionScanner
import org.hachiman.extend.lowerFirst
import org.hachiman.stereotype.Component
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
        val bean1 = beanFactory.getBean("name")
        val bean2 = beanFactory.getBean<Name>("name", Name::class.java)
        val bean3 = beanFactory.getBean<Name>(Name::class.java)
        if (bean1 is Name) {
            println(bean1)
            bean1.printName()
        }
        println(bean2)
        bean2.printName()
        println(bean3)
        bean3.printName()
    }

    @Test
    fun className() {
        val clazz = ScanPackageTest::class.java
        println(clazz.simpleName)
        println(clazz.name)
        println(clazz.canonicalName)
        println(clazz.packageName)
    }

    @Test
    fun testDetermineBeanName() {
        val clazz = ScanPackageTest::class.java
        val component = clazz.getAnnotation(Component::class.java)
        component?.value?.ifBlank { clazz.simpleName.lowerFirst() }
        val s = component?.value ?: clazz.simpleName.lowerFirst()
        println(s)
    }


}