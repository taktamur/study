package com.example.tak.buildingup

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Before
    fun setup(){
        ItemRepository.getItemMap().clear()
    }
    @Test
    fun testItemMap(){
        val itemMap = ItemRepository.getItemMap()
        val item1 = itemMap.find("1")
        assertNull(item1)
        assertEquals(itemMap.all().count(),0)
        itemMap.add("1")
        assertEquals(itemMap.all().count(),1)
        val item2 = itemMap.find("1")
        assertEquals(item2,Item("1"))
        itemMap.add("2")
        val item3 = itemMap.find("2")
        assertEquals(item3,Item("2"))
        itemMap.remove("2")
        assertEquals(itemMap.all().count(),1)
    }

    @Test
    fun json(){
        val itemMap = ItemRepository.getItemMap()
        itemMap.add("1")
        itemMap.add("2")
        var json =itemMap.json()
        println(json)
    }

    @Test
    fun saveAndLode(){
        ItemRepository.add("1")
        ItemRepository.add("2")
        println(ItemRepository.getItemMap())
        ItemRepository.save()
        println(ItemRepository.getItemMap())
        ItemRepository.clear()
        println(ItemRepository.getItemMap())
        ItemRepository.load()
        println(ItemRepository.getItemMap())
        var itemMap = ItemRepository.getItemMap()
        val item3 = itemMap.find("2")
        assertEquals(item3,Item("2"))

    }
    @Test
    fun getClassName(){
        var o:String? = null
//        println(o::class.simpleName)
        o = "hoge"
        println(o::class.simpleName)
        val s:String = o
        println(s::class.simpleName)
        smartcast(null)
        smartcast(1)
        smartcast("hogehoge")

    }

    fun smartcast(a:Any?){
        println("====")
        println(a)
//        println(a::class.simpleName)  // コンパイラがエラーになる
        if( a==null){ return }
        println(a::class.simpleName)
        if( a !is String ){ return }
        println(a::class.simpleName)
        val b = a + "hoge"
    }
}
