package jp.paming.listsample.gallary

import android.content.ContentResolver
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.paming.listsample.databinding.RawPhotoBinding
import kotlinx.android.synthetic.main.activity_recycler.*
import com.squareup.picasso.Picasso
import jp.paming.listsample.R


class PhotoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        onCreatePhotoPermission{
            val photoDataList = read()
            Log.d("photoDataList","${photoDataList}")

            recycleView.layoutManager = LinearLayoutManager(this)
            recycleView.adapter = MyRecycleAdapter(
                this,
                contentResolver,
                photoDataList
            )
        }
    }

    // RecyclerView.ViewHolderを継承した自作ViewHolder
    // 親クラスの初期化にはBinding.rootで親Viewを渡し、
    // 子クラスはbindingを保持する
    class PhotoDataViewHolder(val binding: RawPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    class MyRecycleAdapter(val context: Context,val contentResolver:ContentResolver, val list:List<PhotoData>) : RecyclerView.Adapter<PhotoDataViewHolder>() {
        private val layoutInflater = LayoutInflater.from(context)

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDataViewHolder {
            val binding: RawPhotoBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.raw_photo, parent, false)
            return PhotoDataViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PhotoDataViewHolder, position: Int) {
            // ここではモデルに値をセットしている
            // →DataBindingにより、自動でViewに反映される
            holder.binding.date = list[position].dateString
            Picasso.get().load(list[position].uri).fit().into(holder.binding.imageView)
        }
    }
}
