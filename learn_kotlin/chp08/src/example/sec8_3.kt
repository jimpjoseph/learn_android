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

fun lookForAlice2(people: List<Person>) {
    people.forEach labels@{
        if (it.firstName == "Alice") {
            println("Found !!!")
            return@labels
        }
    }
    println("Alice must be somewhere!!!")
}

fun lookForAlice3(people: List<Person>) {
    people.forEach {
        if (it.firstName == "Alice") {
            println("Found !!!")
            return@forEach
        }
    }
    println("Alice must be somewhere!!!")
}

fun s8_3_1() {
    val people1 = listOf(Person("Alice", "Allison",  null), Person("James", "Joseph", null))
    val people2 = listOf(Person("Jennifer", "Jmames", null), Person("James", "Joseph", null))
    lookForAlice(people1)
    lookForAlice(people2)
    lookForAlice2(people1)
    lookForAlice2(people2)
    lookForAlice3(people1)
    lookForAlice3(people2)
}