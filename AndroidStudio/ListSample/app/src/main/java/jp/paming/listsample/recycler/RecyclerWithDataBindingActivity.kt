package jp.paming.listsample.recycler

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v7.widget.LinearLayoutManager
import jp.paming.listsample.R
import jp.paming.listsample.databinding.CellTextBinding
import kotlinx.android.synthetic.main.activity_recycler.*


class RecyclerWithDataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val data = (0..100).map{
            "No.$it"
        }
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = MyRecycleAdapter(this, data)
    }

    // RecyclerView.ViewHolderを継承した自作ViewHolder
    // 親クラスの初期化にはBinding.rootで親Viewを渡し、
    // 子クラスはbindingを保持する
    class MyViewHolder(val binding: CellTextBinding) : RecyclerView.ViewHolder(binding.root)

    class MyRecycleAdapter(context: Context, val list:List<String>) : RecyclerView.Adapter<MyViewHolder>() {
        private val layoutInflater = LayoutInflater.from(context)

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding:CellTextBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.cell_text, parent, false)
            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // ここではモデルに値をセットしている
            // →DataBindingにより、自動でViewに反映される
            holder.binding.text = list[position]
        }
    }
}
