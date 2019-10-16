package example

fun main(args: Array<String>) {
    s5_1()
}

data class Person(val name: String, val age: Int)

fun findTheOldest(people : List<Person>) {
    var maxAge = 0
    var theOldest : Person? = null

    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun s5_1() {
    val people = listOf(Person("Jim", 51), Person("Nathan",14), Person("Noella", 23), Person("Jenny",47))
    findTheOldest(people)
    println(people.maxBy { it.age })
    println(people.minBy { it.age })
    println(people.minBy (Person::age))
    println(people.maxBy (Person::age))
}