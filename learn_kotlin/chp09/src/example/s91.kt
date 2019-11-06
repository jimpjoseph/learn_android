package example

fun s9_1() {
    println("      9.1       ")
    s9_1_1()
    s9_1_3()
    s9_2_2()
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

fun <T : Number> onHalf(value: T) : Double {
    return value.toDouble() / 2
}

fun <T: Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

fun <T> ensureTrailingPeriod(seq: T)
    where T: CharSequence, T: Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}
fun s9_1_3() {
    println(onHalf(3))
    println(max("Java", "Kotlin"))
    val helloWorld = StringBuilder("Hello, World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)
}

inline fun <reified T> isA(value: T) = value is T
fun s9_2_2() {
    println(isA<String>("abc"))
    //println(isA<String>(124))
    val items = listOf("one", 2,"three")
    println(items.filterIsInstance<String>())
}
