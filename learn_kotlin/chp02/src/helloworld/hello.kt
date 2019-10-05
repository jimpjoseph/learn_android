package helloworld

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
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}