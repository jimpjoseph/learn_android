package examples

import geometry.shapes.Rectangle
import geometry.shapes.createRandomRectange

fun main(args: Array<String>) {
    println("Hello, World!")
    println("Max of 4 & 3 is : " + max(4,3))

    val name = if (args.size > 0 ) args[0] else "Kotlin"
    println("Hello, $name!")
    if (args.size > 0) {
        println("Hello, $args[0]!")
    }
    println("Hello, ${if (args.size> 0) args[0] else "something else" }!")

    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarried)

    val rect1  = Rectangle(41,43)
    val rect2  = Rectangle(41,41)
    println(rect1.isSquare)
    println(rect2.isSquare)
    println(createRandomRectange().isSquare)
    println(getMnemonic(Color.BLUE))
    println(getWarmth(Color.YELLOW))
    println(mix(Color.BLUE, Color.YELLOW))
    println(mixOptimized(Color.BLUE, Color.YELLOW))
    println(evalNI(Sum(Sum(Num(1), Num(2)),Num(4))))

}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}