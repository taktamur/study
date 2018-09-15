package com.example.tak.buildingup

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

import org.jetbrains.annotations.TestOnly
import java.io.*


data class Item(
        val name:String,
        var timeArray:MutableList<String> = mutableListOf()
)

data class ItemMap(
        var map:MutableMap<String,Item> = mutableMapOf()
):MutableMap<String,Item> by map

object ItemRepository{
    var itemMap = ItemMap()

    @TestOnly
    fun _getItemMap():ItemMap{
        return itemMap
    }

    fun add(name:String){
        val item = Item(name)
        itemMap[name] =item
    }

    fun find(name:String):Item?{
        return itemMap[name]
    }

    fun remove(name:String){
        itemMap.remove(name)
    }
    fun clear(){
        itemMap.clear()
    }

    fun all(): Array<Item> {
        return itemMap.values.toTypedArray()
    }

    fun json(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(itemMap)
    }

    fun save(){
        val mapper = jacksonObjectMapper()

        File("item.json").writeText(this.json())
    }
    fun load(){
        val json =File("item.json").readText()
        val mapper = jacksonObjectMapper()
        // TODO これエラーするからチェック入れる
        itemMap = mapper.readValue<ItemMap>(json)
    }
}





