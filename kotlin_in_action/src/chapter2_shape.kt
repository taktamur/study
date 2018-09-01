package geometory.shapes
import java.util.Random

class Rectangle(val height:Int, val width:Int){
    val isSquare:Boolean
        get() = height==width
}

fun createRandomRectangle():Rectangle{
    val rand = Random()
    return Rectangle(rand.nextInt(),rand.nextInt())
}