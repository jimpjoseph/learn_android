package example

import java.lang.StringBuilder

fun s8_1() {
    println("***  8.1 ***")
    s8_1_1()
    s8_1_2()
    s8_1_4()
}

fun s8_1_1() {
    val sum : (Int, Int) -> Int = { x, y -> x + y}
    val action: () -> Unit = { println(42) }
    var canReturnNull: (Int, Int) -> Int?
    var funOrNull: ((Int, Int) ->Int)? = null
    println(sum(2,4))
    action()
    canReturnNull = {_, _ -> null}
    println(canReturnNull(4,5))
    funOrNull = sum
    println(funOrNull(4,5))
}

fun twoAndThreeOperation(operation: (Int, Int) -> Int) {
    val result = operation(2,3)
    println("The result is ${result}")
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) {
            sb.append(element)
        }
    }
    return sb.toString()
}

fun s8_1_2() {
    println("*** 8.1.2 ***")
    twoAndThreeOperation({a,b -> a + b})
    twoAndThreeOperation({a,b -> a * b})
    twoAndThreeOperation({a,b -> a - b})
    println("a1b2#c3d".filter { it in 'a'..'z' })
}

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: (T) -> String = { it.toString()}
) : String {
    val sb = StringBuilder(prefix)

    for ((index,element) in withIndex()) {
        if (index > 0) sb.append(separator)
        sb.append(transform(element))
    }
    sb.append(postfix)
    return sb.toString()
}

fun <T> Collection<T>.joinToString2(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null
) : String {
    val sb = StringBuilder(prefix)

    for ((index,element) in withIndex()) {
        if (index > 0) sb.append(separator)
        val str = transform?.invoke(element)  ?: element.toString()
        sb.append(str)
    }
    sb.append(postfix)
    return sb.toString()
}

fun s8_1_4() {
    println("*** 8.1.4 ***")
    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString())
    println(letters.joinToString { it -> it.toLowerCase() })
    println(letters.joinToString { it -> it.toUpperCase() })
    println(letters.joinToString2())
    println(letters.joinToString2 { it -> it.toLowerCase() })
    println(letters.joinToString2 { it -> it.toUpperCase() })
}