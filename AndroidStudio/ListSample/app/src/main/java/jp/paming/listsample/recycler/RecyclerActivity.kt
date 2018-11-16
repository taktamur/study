package jp.paming.listsample.recycler

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v7.widget.LinearLayoutManager
import jp.paming.listsample.R
import kotlinx.android.synthetic.main.activity_recycler.*


class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val data = (0..100).map{
            "No."+ it
        }
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = MyRecycleAdapter(this, data)
    }

    // RecyclerView.ViewHolderを継承した自作ViewHolder
    // 親クラスの初期化には親Viewへの参照を渡し、
    // 子クラスのプロパティには子Viewへの参照を保持する
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView:TextView = view.findViewById(R.id.textView)
    }

    class MyRecycleAdapter(private val context: Context, val list:List<String>) : RecyclerView.Adapter<MyViewHolder>() {
        private val layoutInflater = LayoutInflater.from(context)
        // 件数を返す
        override fun getItemCount(): Int {
            return list.size
        }
        // Viewに対応するViewHolderを生成して返す
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = layoutInflater.inflate(R.layout.cell_text, parent, false)
            return MyViewHolder(view)
        }
        // ViewHolderを使って、Viewの更新を行う
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = list[position]
        }
    }
}
