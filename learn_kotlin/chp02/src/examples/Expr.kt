package examples

import java.lang.IllegalArgumentException

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr): Expr


// Eval non-idiomatic
fun evalNI(e: Expr) : Int {
    if (e is Num) {
        return e.value
    }
    if (e is Sum) {
        return evalNI(e.left) + evalNI(e.right)
    }
    throw IllegalArgumentException("Unknown expression")
}

// Eval - idomatic version with If

fun eval1(e: Expr) : Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval1(e.left) + eval1(e.right)
    } else {
        throw IllegalArgumentException("Unkown expression")
    }

// Eval - version using when

fun eval(e: Expr): Int =
    when(e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }