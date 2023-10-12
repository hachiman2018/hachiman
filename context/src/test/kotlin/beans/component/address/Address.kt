package beans.component.address

import org.hachiman.stereotype.Component


@Component(value = "myAddress")
class Address {

    fun printAddress() {
        println("chongqing")
    }
}