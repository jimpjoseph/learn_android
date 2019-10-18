package examples

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

