package examples

import geometry.shapes.Rectangle
import geometry.shapes.createRandomRectange
import java.util.*

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
    println(eval1(Sum(Sum(Num(1), Num(2)),Num(4))))
    println(eval(Sum(Sum(Num(1), Num(2)),Num(4))))
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)),Num(4))))

    for( i in 1..100) {
        print(fizzBuzz(i))
    }
    println("")
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
    println("")

    mapExample()

    println(isLetter('q'))
    println(isNotDigit('4'))
    println(recognize('8'))
    println("Kotlin" in "Java".."Scala")
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun mapExample() {
    val binaryHeap = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryHeap[c] = binary
    }
    for ((letter, binary) in binaryHeap) {
        println("$letter = $binary")
    }

    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index = $element")
    }
}

fun isLetter(c : Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c : Char) = c !in '0'..'9'

fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "it's a digit"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "I don't know"
}