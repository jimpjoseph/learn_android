package examples

import java.math.BigDecimal

data class Point(val x: Int, val y : Int)

operator fun Point.plus(other :Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.times(scale: Double): Point {
    return Point( (x*scale).toInt(), (y*scale).toInt())
}

operator fun Double.times(p : Point): Point {
    return Point((toDouble() * p.x).toInt(), (toDouble() * p.y).toInt())
}

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun s7_1_1() {
    println("7.1.1")
    val p1 = Point(10,20)
    val p2 = Point( 30, 40)
    println(p1 + p2)
    println(p1 * 1.5)
    println(1.5 * p2)
    println('c' * 5)
    println(0x0F and 0xF0)
    println(0x0F or 0xF0)
    println(0x01 shl 4)
}

class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this,other, Person::lastName, Person::firstName)
    }
}

fun s7_1_2() {
    println("7.1.2")
    var point = Point(1,2)
    point += Point(3,4)
    println(point)

    val list = arrayListOf(1,2)
    list += 3
    val newList = list + listOf(4,5)
    println(list)
    println(newList)
    println(-point)
    var bd = BigDecimal.ZERO
    println(bd++)
    println(++bd)

    val p1 = Person("James", "Joseph")
    val p2 = Person("Jennifer", "James")

    println(p1<p2)
}

