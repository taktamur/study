package com.example.tak.buildingup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row.*
import android.nfc.NfcAdapter.EXTRA_DATA
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher


class DetailActivity : AppCompatActivity() , TextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    val itemMap = ItemRepository.getItemMap()
    var itemKey:ItemMapKey? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val position = intent.getIntExtra("itemposition", 0)
        val item = itemMap.all()[position]
        itemKey = item.name
        editText.setText(item.name,TextView.BufferType.EDITABLE)
        editText.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable) {
        itemKey?.let{
            itemMap.find(it)?.let {
                it.name = s.toString()
            }
        }
    }

}
