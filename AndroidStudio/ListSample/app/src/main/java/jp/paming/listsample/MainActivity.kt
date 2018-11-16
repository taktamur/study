package jp.paming.listsample

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import jp.paming.listsample.card.PhotoCardActivity
import jp.paming.listsample.card.SimpleCardViewActivity
import jp.paming.listsample.gallary.PhotoListActivity
import jp.paming.listsample.list.ListViewActivity
import jp.paming.listsample.list.ListViewWithDataBindingActivity
import jp.paming.listsample.recycler.RecyclerActivity
import jp.paming.listsample.recycler.RecyclerWithDataBindingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_text.view.*

class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val adapter = MainListAdapter(this)
            adapter.apply {
                add("ListView")
                add("ListView+DataBinding")
                add("RecyclerView")
                add("RecyclerView+DataBinding")
                add("PhotoListActivity")
                add("SimpleCardViewActivity")
                add("PhotoCardActivity")
            }
            list_view.adapter = adapter
            list_view.setOnItemClickListener { parent, view, position, id ->
                val item = adapter.getItem(position)
                item?.run {
                    val context = applicationContext
                    Toast.makeText(context, item, Toast.LENGTH_LONG).show()
                    val intent =when(position){
                        0 -> Intent(context, ListViewActivity::class.java)
                        1 -> Intent(context, ListViewWithDataBindingActivity::class.java)
                        2 -> Intent(context, RecyclerActivity::class.java)
                        3 -> Intent(context, RecyclerWithDataBindingActivity::class.java)
                        4 -> Intent(context, PhotoListActivity::class.java)
                        5 -> Intent(context, SimpleCardViewActivity::class.java)
                        6 -> Intent(context, PhotoCardActivity::class.java)
                        else -> return@run
                    }
                    startActivity(intent)
                }
            }
        }

        class MainListAdapter(context: Context): ArrayAdapter<String>(context,0){
            private val layoutInflater = LayoutInflater.from(context)
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemView = layoutInflater.inflate(R.layout.cell_text,parent,false)
                val item = getItem(position)
                itemView.textView.text = item
                return itemView
            }
        }
}
