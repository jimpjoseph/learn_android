package example

fun s8_1() {
    println("***  8.1 ***")
    s8_1_1()
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