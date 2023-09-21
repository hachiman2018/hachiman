import org.hachiman.stereotype.Component
import org.hachiman.util.scanPackage
import org.junit.jupiter.api.Test
import org.junit.platform.commons.util.ClassUtils

class Test {


    @Test
    fun main(): Unit {

        scanPackage("beans", Component::class.java)
    }


}