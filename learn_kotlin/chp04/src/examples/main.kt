package examples

import java.io.File

fun main(args: Array<String>){
    println("Chapter 04")
    s4_1()
    s4_3()
    s4_3_3()
    s4_4()
}

fun s4_1() {
    Button().click()
    Button().showOff()

    val but = Button()
    but.click()
    but.setFocus(true)
    but.showOff()
    but.setFocus(false)

    println(eval(Expr.Sum(Expr.Num(1), Expr.Sum(Expr.Num(2),Expr.Num(3)))))
}

class Client(val name:String, val postalCode : Int) {
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode.hashCode()
    override fun toString(): String = "Client(name=$name, postalCode=$postalCode)"
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name && postalCode == other.postalCode
    }
}

data class ClientD(val name:String, val postalCode : Int)

fun s4_3() {
    val client1 = Client("James Joseph", 60606)
    println(client1)
    val client2 = Client("James Joseph", 60606)
    println(client1 == client2)

    val processed = hashSetOf(client1)
    println(processed.contains(client2))

    val clientd1 = ClientD("James Joseph", 60606)
    println(clientd1)
    val clientd2 = ClientD("James Joseph", 60606)
    println(clientd1 == clientd2)

    val processedd = hashSetOf(clientd1)
    println(processedd.contains(clientd2))

    println(clientd1.copy(postalCode = 60047))
}

fun s4_3_3() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,1,2))
    println("${cset.objectAdded} objects were added, ${cset.size} remain")
}

fun s4_4() {
    println(CaseInsensitiveFileComparator.compare(File("/user"), File("/USER")))
    val files = listOf(File("/Z"), File("/a"), File("/M"), File("/h"))
    println(files.sortedWith(CaseInsensitiveFileComparator))
    val persons = listOf(Person("James"), Person("Jennifer"), Person("Noella"), Person("Nathan"))
    println(persons.sortedWith(Person.NameComparator))

    val subscribingUser = User.newSubscribingUser("james.joseph@here.com")
    println(subscribingUser)
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, true)
    }
}

data class Person(val name: String) {
    object NameComparator: Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int {
            return p1.name.compareTo(p2.name)
        }
    }
}

class User private constructor(val nickName: String) {
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
    }

    override fun toString(): String {
        return nickName
    }
}