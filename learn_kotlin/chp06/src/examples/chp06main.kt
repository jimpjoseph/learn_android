package examples

fun main(args: Array<String>) {
    s6_1_1()
    s6_1_3()
}

fun strLen(s: String?) = if (s != null) s.length else 0
fun s6_1_1() {
    println(strLen("James"))
    println(strLen(null))
}

fun printAllCaps(s: String?) {
    val allCaps = s?.toUpperCase()
    println(allCaps)
}

class Employee(val name:String, val manager: Employee?)
fun managerName(employee: Employee) : String? = employee.manager?.name

fun strLenSafe(s: String?) : Int = s?.length ?: 0

fun s6_1_3() {
    printAllCaps("jim")
    printAllCaps(null)
    val ceo = Employee("Da Boss", null)
    val dev = Employee("Bob Smith", ceo)
    println(managerName(dev))
    println(managerName(ceo))
    println(strLenSafe("jim"))
    println(strLenSafe(null))
}