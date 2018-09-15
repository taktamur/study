package com.example.tak.buildingup

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_list.*
import android.content.Intent
import android.R.attr.author
import android.content.Context
//import java.awt.print.Book
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.*
import android.widget.*
import android.view.LayoutInflater




class ListActivity : AppCompatActivity() {
    private val texts = listOf("abc ", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm",
            "abc ", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm")

    private var count = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)
        ItemRepository.add("1")
        ItemRepository.add("2")
        val itemAdapter = ItemAdapter(this, ItemRepository.all())
        list.setAdapter(itemAdapter)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            ItemRepository.add("hogehoge$count")
            count = count+1
            println(ItemRepository.all())
            itemAdapter.setMytemList(ItemRepository.all())
            itemAdapter.notifyDataSetChanged()
        }

        list.setOnItemClickListener { parent, view, position, id ->
            startActivity(Intent(this, DetailActivity::class.java))

        }
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

    private inner class ItemAdapter(context:Context,var itemList:Array<Item>) : BaseAdapter() {
        val layoutInflater: LayoutInflater
        init {
            this.layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        override fun getCount(): Int {
            return itemList.count()
        }

        override fun getItem(position: Int): Item {
            return itemList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        fun setMytemList(itemList: Array<Item>) {
            this.itemList = itemList
        }

        override fun getView(
                position: Int,
                convertView: View?,
                parent: ViewGroup): View {

            val textView1: TextView

            val v = convertView ?: layoutInflater.inflate(R.layout.row,parent,false)

            val item = getItem(position)
            if (item != null) {
                textView1 = v.findViewById(R.id.textView1) as TextView
                textView1?.setText(item.name)
            }
            return v
        }

    }
}
