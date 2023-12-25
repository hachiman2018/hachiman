package base

import org.junit.jupiter.api.Test

class BastTest {

    @Test
    fun arrayTest() {
        val range = 1..10
        range.forEach {
            println(it)
        }
    }


    @Test
    fun listTest() {
        val list = listOf(1, 2, 3)
        list.forEach {
            println(it)
        }

        val mutableListOf = mutableListOf<Int>()

        mutableListOf.add(10)
        mutableListOf.add(9)

        mutableListOf += 20

        mutableListOf.forEach {
            println(it)
        }

        val numbers = mutableListOf(1, 2, 3, 4)
        numbers.also {
            it.add(5)
        }.also {
            println("List after addition: $it")
        }


    }

    @Test
    fun mapTest() {
        val map = mapOf(2 to "name")
        println(map[1])

        // 自定义的方法 componentN 获取结构参数
        for ((key, item) in map) {
            println("key is $key, value is $item")

        }
    }

    class Person(val name: String) {
        val emials: List<Int> by lazy {
            arrayListOf()
        }
    }

}