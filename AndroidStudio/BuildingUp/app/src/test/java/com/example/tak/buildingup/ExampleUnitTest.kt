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
    fun clear(){
        ItemRepository.clear()
    }
    @Test
    fun repository(){
        val item1 = ItemRepository.find("1")
        assertNull(item1)
        assertEquals(ItemRepository.all().count(),0)
        ItemRepository.add("1")
        assertEquals(ItemRepository.all().count(),1)
        val item2 = ItemRepository.find("1")
        assertEquals(item2,Item("1"))
        ItemRepository.add("2")
        val item3 = ItemRepository.find("2")
        assertEquals(item3,Item("2"))
        ItemRepository.remove("2")
        assertEquals(ItemRepository.all().count(),1)
    }

    @Test
    fun json(){
        ItemRepository.add("1")
        ItemRepository.add("2")
        var json =ItemRepository.json()
        println(json)
    }

    @Test
    fun saveAndLode(){
        ItemRepository.add("1")
        ItemRepository.add("2")
        println(ItemRepository._getItemMap())
        ItemRepository.save()
        println(ItemRepository._getItemMap())
        ItemRepository.clear()
        println(ItemRepository._getItemMap())
        ItemRepository.load()
        println(ItemRepository._getItemMap())
        val item3 = ItemRepository.find("2")
        assertEquals(item3,Item("2"))

    }
}
