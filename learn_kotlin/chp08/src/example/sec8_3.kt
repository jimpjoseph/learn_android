package example

fun s8_3() {
    println("**** 8. 3 ***")
    s8_3_1()
}

fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.firstName == "Alice") {
            println("Found!")
            return;
        }
    }
    println("Alice is Not found")
}
fun s8_3_1() {
    val people1 = listOf(Person("Alice", "Allison",  null), Person("James", "Joseph", null))
    val people2 = listOf(Person("Jennifer", "Jmames", null), Person("James", "Joseph", null))
    lookForAlice(people1)
    lookForAlice(people2)
}