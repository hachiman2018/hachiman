package beans

import org.hachiman.stereotype.Component
import org.hachiman.stereotype.Scope


@Component
@Scope("property")
class Name {

    fun printName() {
        println("test naming")
    }
}