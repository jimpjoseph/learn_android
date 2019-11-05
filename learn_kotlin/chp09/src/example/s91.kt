package example

fun s9_1() {
    println("      9.1       ")
    s9_1_1()
}


val <T> List<T>.penultimate: T
    get() = this[size - 2]

fun s9_1_1() {
    println("      9.1.1       ")
    val letters = ('a'..'z').toList()
    println(letters.slice<Char>(0..2))
    println(letters.slice(10..13))
    println(listOf(1,2,3,4).penultimate)
}