package example

import java.lang.StringBuilder

fun main(args : Array<String>) {
    s3_1()
    s3_2()
}

fun s3_2() {
    val list = listOf(1,2,3)
    println(list)
    println(list.joinToString())
    println(list.joinToString("| "))
    println(list.joinToString("; ", "(", ")"))
    println(list.joinToString(prefix = "# ", postfix = "; "))
    println("Kotlin".lastChar())
    //println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)

}



fun s3_1() {
    val set = hashSetOf(1,7,53)
    println(set.javaClass)

    val list = arrayListOf(1,7,5)
    println(list.javaClass)

    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty three")
    println(map.javaClass)

    val strings = listOf("first", "second", "fourtheent")
    println(strings.last())

    val numbers = setOf(1, 14, 2)
    println(numbers.max())
}