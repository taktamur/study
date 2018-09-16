package com.example.tak.buildingup

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

import java.io.*


data class Item(
        var name:ItemMapKey,
        var timeArray:MutableList<String> = mutableListOf()
)

typealias ItemMapKey = String
// TODO Item Mapである必要はなくてArrayでも良さそう
data class ItemMap(
        var map:MutableMap<ItemMapKey,Item> = mutableMapOf()
):MutableMap<ItemMapKey,Item> by map {

    fun add(name: String) {
        val item = Item(name)
        this[name] = item
    }

    fun find(name:String):Item?{
        return this[name]
    }

//    fun remove(name:String){
//        this.remove(name)
//    }
//    fun clear(){
//        this.clear()
//    }

    fun all(): Array<Item> {
        return this.values.toTypedArray()
    }

    fun json(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)
    }

}

object ItemRepository{
    var _itemMap = ItemMap()

    fun getItemMap():ItemMap{
        return _itemMap
    }

    fun add(name:String){
        _itemMap.add(name)
    }

    fun find(name:String):Item?{
        return _itemMap[name]
    }

    fun remove(name:String){
        _itemMap.remove(name)
    }
    fun clear(){
        _itemMap.clear()
    }

    fun all(): Array<Item> {
        return _itemMap.all()
    }

    fun json(): String {
        return _itemMap.json()
    }

    fun save(){
        val mapper = jacksonObjectMapper()

        File("item.json").writeText(this.json())
    }
    fun load(){
        val json =File("item.json").readText()
        val mapper = jacksonObjectMapper()
        // TODO これエラーするからチェック入れる
        _itemMap = mapper.readValue<ItemMap>(json)
    }
}





