import java.lang.Runtime.*
import kotlin.system.measureTimeMillis

fun test1(){
    fun memory():String{
        val mb = (getRuntime().totalMemory()-getRuntime().freeMemory())/(1024*1024)
        return "$mb MB"
    }
    // Kotlin インアクション P.156
    // 先行評価
    var ret1:List<String> = listOf()
    val elapsed1 = measureTimeMillis {
        ret1 = (0..1000000000).filter { it % 10000000 == 0 }.map { memory() }
    }
    println(ret1.joinToString("\n"))
    println(elapsed1)

    println("========")

    // 遅延評価
    var ret2:Sequence<String> = sequenceOf()
    val elapsed2 = measureTimeMillis {
        ret2 = (0..1000000000).asSequence().filter { it % 10000000 == 0 }.map { println(it);memory() }
        // この時点では、まだfilterやmapやmemoryは動作していない
    }// だからmeasureTimeMillsは異様に早く終わる
    println("end ret2") // ここでもまだ動いていない
    println("ret2.count=${ret2.count()}")  // ここで動く(1回目)
    println(ret2.joinToString("\n")) // ここでも動く(2回目)
    println(elapsed2)
}

fun test2(){
    fun memory():String{
        val mb = (getRuntime().totalMemory()-getRuntime().freeMemory())/(1024*1024)
        return "$mb MB"
    }

    var ret1:List<String> = listOf()
    val elapsed1 = measureTimeMillis {
        ret1 = (0..1000000000).filter { it % 10000000 == 0 }.map { memory() }
    }
    println(ret1.joinToString("\n"))  // 均一なメモリ量を返している（変化していない）
    println(elapsed1)

    println("========")

    // 遅延評価
    var ret2:List<String> = listOf()
    val elapsed2 = measureTimeMillis {
        ret2 = (0..1000000000).asSequence().filter { it % 10000000 == 0 }.map { memory() }.toList()
    }
    println(ret2.joinToString("\n"))  // バラバラなメモリ量を返している
    println(elapsed2)
}


fun main(args: Array<String>) {
    test2()
}