package examples

fun main(args: Array<String>) {
    s6_1_1()
    s6_1_3()
    s6_1_4()
    s6_1_7()
    s6_1_9()
    s6_1_10()
    s6_2_1()
    s6_2_2()
    s6_2_3()
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

class Person(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person ?: return false
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int {
        return firstName.hashCode() *31 + lastName.hashCode()
    }
}

fun s6_1_4() {
    val p1  = Person("James", "Joseph")
    val p2 = Person("James", "Joseph")
    println(p1.equals(p2))
    println(p1.equals("James Joseph"))
}

fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun s6_1_7() {
    val email1 : String? = "james.joseph@gmail.com"
    email1?.let { sendEmailTo(it) }

    val email2 : String? = null
    email2?.let { sendEmailTo(it) }
}

fun String?.isNullOrBlank(): Boolean =
    this == null || this.isBlank()

fun valdiateInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please full in the required fields")
    }
}
fun s6_1_9() {
    valdiateInput(("jim"))
    valdiateInput("      ")
    valdiateInput(null)
}

fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}

fun <T: Any> printHashCode2(t: T) {
    println(t.hashCode())
}
fun s6_1_10() {
    printHashCode("String")
    printHashCode(null)
    printHashCode2("String")
    //printHashCode2(null)
}

fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0,100)
    println("We're ${percent}% done!")
}

fun s6_2_1() {
    showProgress(146)
}

data class PersonD(val name: String,
                val age: Int? = null) {
    fun isOlderThan(other: PersonD): Boolean? {
        if (age == null || other.age == null) {
            return null
        }
        return age > other.age
    }
}

fun s6_2_2() {
    println(PersonD("Sam",35).isOlderThan(PersonD("Amy",42)))
    println(PersonD("Sam",35).isOlderThan(PersonD("Jane")))

}

fun foo(l: Long) = println(l)

fun s6_2_3() {
    val i = 1
    //val l : Long = i
    val l: Long = i.toLong()
    val x = 1
    val list = listOf(1L, 2L, 3L)
    //println(x in list)  -- this does not complie as implicit conversion is not supported
    println(x.toLong() in list)
    val b: Byte = 1
    val l1 = b + 1L
    foo(l1)
    foo(42)
    println("434".toInt())
}