package examples

fun main(args: Array<String>) {
    s6_1_1()
}

fun strLen(s: String?) = if (s != null) s.length else 0
fun s6_1_1() {
    println(strLen("James"))
    println(strLen(null))
}