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