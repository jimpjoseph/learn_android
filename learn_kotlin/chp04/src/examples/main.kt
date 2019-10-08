package examples

fun main(args: Array<String>){
    println("Chapter 04")
    s4_1()
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