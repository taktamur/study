package jp.paming.listsample.list

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.TextView
import jp.paming.listsample.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    lateinit var adapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val data = (0..100).map{
            "No."+ it
        }
        adapter = ListAdapter(this, data)
//        adapter = ListViewActivity.ListAdapterWithViewHolder(this,data)

        listView.adapter = adapter
    }
/*
    // 右上のメニューの組み立て
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_view_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menuItem1 -> {
                // adapterにinsertしたら画面が更新される
                // これはinserの中で、notifyDataSetChanged()が呼ばれているから
                adapter.insert("ADD"+adapter.count,0)
                true
            }
            R.id.menuItem2 -> {
                adapter.remove(adapter.getItem(0))
                true
            }
            else->{
                super.onOptionsItemSelected(item)
            }
        }
    }
    */

    class ListAdapter(context: Context, val list:List<String>): ArrayAdapter<String>(context,0,list){
        private val layoutInflater = LayoutInflater.from(context)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val itemView = layoutInflater.inflate(R.layout.cell_text,parent,false)
            val textView = itemView.findViewById(R.id.textView) as TextView
            textView.text = getItem(position)
            return itemView
        }
    }

    // View.tagにセットするViewHolder
    data class ViewHolderItem(val textView:TextView)

    class ListAdapterWithViewHolder(context: Context,val list:List<String>): ArrayAdapter<String>(context,0,list){
        private val layoutInflater = LayoutInflater.from(context)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val (viewHolder, view) = if(convertView == null) {
                val itemView = layoutInflater.inflate(R.layout.cell_text,parent,false)
                val textView = itemView.findViewById(R.id.textView) as TextView
                val viewHolder = ViewHolderItem(textView)
                itemView.tag = viewHolder
                viewHolder to itemView
            } else {
                convertView.tag as ViewHolderItem to convertView
            }

            viewHolder.textView.text = getItem(position)
            return view
        }
    }
}


