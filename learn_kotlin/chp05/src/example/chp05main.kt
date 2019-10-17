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

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0

    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}

fun s5_1() {
    val people = listOf(Person("Jim", 51), Person("Nathan",14), Person("Noella", 23), Person("Jenny",47))
    findTheOldest(people)
    println(people.maxBy { it.age })
    println(people.minBy { it.age })
    println(people.minBy (Person::age))
    println(people.maxBy (Person::age))

    val sum = {x: Int, y: Int -> x + y}
    println("Sum is ${sum(10, 43)}")

    run { println(42) }
    println(people.maxBy { p : Person -> p.age })

    println(people.joinToString (separator = " ", transform = {p : Person -> p.name}))
    println(people.joinToString(" "){p: Person -> p.name})
    println(people.joinToString(" "){p -> p.name})
    println(people.joinToString(" "){ it.name})

    val errors = listOf("403 Forbidden", "404 not found", "401 Unauthorized")

    printMessagesWithPrefix(errors, "Error:")

    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal server error")
    printProblemCounts(responses)
}