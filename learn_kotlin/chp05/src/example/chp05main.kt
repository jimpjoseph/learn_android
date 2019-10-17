package example

fun main(args: Array<String>) {
    s5_1()
    s5_2_1()
    s5_2_2()
    s5_2_3()
    s5_2_4()
    s5_3_1()
    s5_3_2()
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

fun Person.isAdult() = age >= 21

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

    val createPerson = ::Person
    val p = createPerson("Alice", 29)
    println(p)
    val predicate = Person::isAdult
    println(predicate(p))

    //Bound reference
    val p1 = Person("Dmitry", 34)
    val personAgeFunction = Person::age
    println(personAgeFunction(p1))

    //Only after kotlin 1.1
    val dmitryAgeFunction = p1::age
    println(dmitryAgeFunction());

}




fun s5_2_1() {
    val list = listOf(1,2,3,4)
    println(list.filter{it %2 == 0})
    val people = listOf(Person("Alice", 29), Person("Bob", 34))
    println(people.filter { it.age > 30 })
    println(list.map { it * it })
    println(people.map {it.name})
    println(people.map(Person::name))
    println(people.filter { it.age > 30 }.map { it.name })
    println(people.filter { it.age == people.maxBy(Person::age)?.age }) // in-efficent version
    val maxAge = people.maxBy(Person::age)?.age
    println(people.filter { it.age == maxAge })

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.toUpperCase() })
}

fun s5_2_2() {
    val canBeInClub27 = { p: Person -> p.age <= 27 }
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    println(people.all(canBeInClub27))
    println(people.any(canBeInClub27))
    val list = listOf(1,2,3)
    println(!list.all { it == 3 })
    println(list.any{it != 3})
    println(people.count(canBeInClub27))
    println(people.find(canBeInClub27))

}

fun s5_2_3() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31), Person("Carol", 31))
    println(people.groupBy { it.age })

    val list = listOf("a", "ab", "b")
    println(list.groupBy ( String::first ))
}

class Book(val title : String, val authors : List<String>)

fun s5_2_4() {
    val books = listOf(Book("Of Jim and Jenny", listOf("James", "Jenny")), Book("600 Grethe", listOf("James", "Jenny", "Noella", "Nathan")))
    println(books.flatMap { it.authors }.toSet())
    val strings = listOf("abc","def")
    println(strings.flatMap { it.toList() })
}

fun s5_3_1() {
    println(listOf(1,2,3,4).asSequence()
        .map { print("map $it "); it *it}
        .filter { print("filter $it ") ; it %2 == 0}
        .toList())
    println(listOf(1,2,3,4).asSequence().map{it*it}.find{it > 3})

    val people = listOf(Person("Alice", 29), Person("Bob", 31),
        Person("Charles",31), Person("Dan",21))
    println(people.asSequence().map(Person::name).filter { it.length < 4 }.toList())
    println (people.asSequence().filter { it.name.length < 4}.map(Person::name).toList())
}

fun s5_3_2() {
    val naturalNumbers = generateSequence(0) { it + 1  }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())
}