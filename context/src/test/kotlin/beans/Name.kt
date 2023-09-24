package beans

import org.hachiman.context.annotation.Scope
import org.hachiman.stereotype.Component


@Component
@Scope("property")
class Name {

    fun printName() {
        println("test naming")
    }
}