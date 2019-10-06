package examples

import java.lang.Exception

//Example of enum

enum class Color (val r: Int, val g: Int, val b: Int){
    RED(255,0,0),
    ORANGE(255, 165, 9),
    YELLOW (255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0,0, 255),
    INDIGO(75, 0, 255),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 255 + g) * 255 + b
}

//when
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

//when - combining options into one
fun getWarmth(color: Color) =
    when(color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "Warm"
        Color.GREEN -> "Neutral"
        Color.BLUE, Color.VIOLET, Color.INDIGO -> "cold"
    }


// when -  with arbitrary objects
fun mix(c1: Color, c2: Color) =
    when(setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty Color")
    }

// when - without any arguments


fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == Color.RED && c2 == Color.YELLOW) ||
            (c1 == Color.YELLOW && c1 == Color.RED) -> Color.ORANGE
    (c1 == Color.YELLOW && c2 == Color.BLUE) ||
            (c1 == Color.BLUE && c2 == Color.YELLOW) -> Color.GREEN
    (c1 == Color.BLUE && c2 == Color.VIOLET) ||
            (c1 == Color.VIOLET && c2 == Color.BLUE) -> Color.INDIGO
    else
        -> throw Exception("Dirty Color")
}