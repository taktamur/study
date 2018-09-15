package com.example.tak.buildingup

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_list.*
import android.widget.ArrayAdapter
import android.widget.ListView


class ListActivity : AppCompatActivity() {
    private val texts = mutableListOf("abc ", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm",
            "abc ", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, texts)

        list.setAdapter(arrayAdapter)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            arrayAdapter.add("hogehoge")
        }

//        val listView = ListView(this)
//        setContentView(listView)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
