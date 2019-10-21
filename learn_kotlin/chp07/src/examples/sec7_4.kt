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
}