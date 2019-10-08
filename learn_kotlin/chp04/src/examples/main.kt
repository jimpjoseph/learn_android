package examples

fun main(args: Array<String>){
    println("Chapter 04")
    s4_1()
    s4_3()
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
}