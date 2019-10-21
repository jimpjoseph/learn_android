package examples

data class NameComponents(val name: String, val extension: String)

fun splitFileName(fullName: String) : NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}


fun s7_4() {
    println("**** 7.4 *****")
    val p = Point(10,20)
    val (x,y) = p
    println(x)
    println(y)

    val (name, ext) = splitFileName("example.kt")
    println(name)
    println(ext)

    s7_4_1()
}

fun printEntries(map: Map<String, String>) {
    for ((k,v) in map) {
        println("$k -> $v")
    }
}


fun s7_4_1() {
    println("***** 7.4.1 *****")
    val map = mapOf("Oracle" to "Java", "Intelli" to "Kotlin", "Apple" to "Swift", "Google" to "go")
    printEntries(map)

}