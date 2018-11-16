package jp.paming.databinding

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import jp.paming.databinding.databinding.ActivityMainBinding
import jp.paming.databinding.databinding.ListExampleBinding
import java.util.*

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val adapter = ExampleAdapter(this)
        adapter.items = listOf(
            ListItem("ゴミ出し", Date()),
            ListItem("技術書を書く",Date()),
            ListItem("脱稿する",Date())
        )
        binding.recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recycleView.adapter = adapter

    }
}

class ExampleAdapter(context:Context): RecyclerView.Adapter<Holder>(){
    var items:List<ListItem> = emptyList()
    private var inflater =LayoutInflater.from(context)
    override fun getItemCount(): Int  = items.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val binding:ListExampleBinding = DataBindingUtil.inflate(inflater, R.layout.list_example,p0,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.item =items[position]
        holder.binding.executePendingBindings()
    }
}

class Holder(val binding:ListExampleBinding) : RecyclerView.ViewHolder(binding.root)

class MainListAdapter(context: Context): ArrayAdapter<ListItem>(context,0){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding:ListExampleBinding
        if( convertView ==null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.list_example,parent,false)
            binding.root.tag = binding
        } else{
            binding = convertView.tag as ListExampleBinding
        }
        binding.item = getItem(position)
        return binding.root
    }
}