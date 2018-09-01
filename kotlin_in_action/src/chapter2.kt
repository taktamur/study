import java.io.BufferedReader
import java.io.StringReader
import java.util.*

//fun main(args:Array<String>) {
//    println("Heello, ${if(args.size>0)args[0] else "someone"}!")
///*
//    val name = if( args.size>0) args[0] else "Bob"
//    println("Hello world $name!")
//*/
//}

fun max(a: Int, b: Int): Int {
    return if (b < a) a else b
}

fun max2(a: Int, b: Int) = if (b < a) a else b

class Person(
        val name: String,
        var isMarried: Boolean
)

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 128, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);


    fun rgb(): Int = (r * 256 + g) * 256 + b
}
// 次、2.3.2から。

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

fun getWarmth(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "Warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
    setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
    setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
    else -> throw Exception("Dirty color")
}

fun mixOptimize(c1: Color, c2: Color) = when {
    (c1 == Color.RED && c2 == Color.YELLOW) ||
            (c1 == Color.YELLOW && c2 == Color.RED) ->
        Color.ORANGE
    (c1 == Color.YELLOW && c2 == Color.BLUE) ||
            (c1 == Color.BLUE && c2 == Color.YELLOW) ->
        Color.GREEN
    (c1 == Color.BLUE && c2 == Color.VIOLET) ||
            (c1 == Color.VIOLET && c2 == Color.BLUE) ->
        Color.INDIGO
    else -> throw Exception("dirty color")
}


sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int = when (e) {
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.left) + eval(e.right)
}

fun evalWithLogging(e: Expr): Int = when (e) {
    is Expr.Num -> {
        println("num:${e.value}")
        e.value
    }
    is Expr.Sum -> {
        val l = evalWithLogging(e.left)
        val r = evalWithLogging(e.right)
        println("sum: $l + $r = ${l + r}")
        l + r
    }
}


fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> println("FizzBuzz")
    i % 3 == 0 -> println("Fizz")
    i % 3 == 0 -> println("Buzz")
    else -> println("$i")
}


fun isLetter(c:Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c:Char) = c !in '0'..'9'

fun recognize(c:Char) = when(c) {
    in 'a'..'z', in 'A'..'Z' -> "is Letter"
    in '0'..'9' -> "is Digit"
    else->"unknown"
}


//fun main(args: Array<String>) {
//    println(recognize('a'))
//    println(recognize('0'))
//    println("Kotlin" in "Java".."Scala")  // Comparableインタフェースを継承しているからできる
//    println("Kotlin" in setOf("Java","Scala"))
//    val number = 99
//    val persentage =
//            if( number in 0..100)
//                number
//            else
//                throw IllegalArgumentException("persentage must be between 0 and 100, $number")
//}

fun readNumber(reader:BufferedReader){
    val number = try{ // tryも値を返す
        val line = reader.readLine()
        Integer.parseInt(line)
    }catch(e:NumberFormatException) {
        return
    }
    println(number)
}
//
//fun main(args:Array<String>){
//    val reader = BufferedReader(StringReader("132a"))
//    readNumber(reader)
//}
