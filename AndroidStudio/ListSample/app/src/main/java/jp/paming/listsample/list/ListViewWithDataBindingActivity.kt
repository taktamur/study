package jp.paming.listsample.list

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import jp.paming.listsample.R
import jp.paming.listsample.databinding.CellTextBinding
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewWithDataBindingActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val data = (0..100).map{
            "No."+ it
        }
        adapter = ListAdapter(this, data)
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
            val binding = if( convertView == null) {
                val binding:CellTextBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.cell_text, parent, false)
                binding.root.tag = binding
                binding
            }else{
                convertView.tag as CellTextBinding
            }
            binding.text = getItem(position)
            return binding.root
        }
    }
}


