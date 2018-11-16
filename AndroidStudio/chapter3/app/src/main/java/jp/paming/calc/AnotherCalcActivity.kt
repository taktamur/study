package jp.paming.calc

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_another_calc.*
//import kotlinx.android.synthetic.main.activity_main.*

class AnotherCalcActivity : AppCompatActivity() , TextWatcher, View.OnClickListener {


    // ライフサイクル
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another_calc)
        numberInput1.addTextChangedListener(this)
        numberInput2.addTextChangedListener(this)
        backButton.setOnClickListener(this)
    }


    // TextWatcher
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        refreshResult()
    }

    // OnClick
    override fun onClick(v: View?) {
        if( !checkEditTextInput()){
            setResult(Activity.RESULT_CANCELED)
        }else{
            val result = calc()
            var data =Intent()
            data.putExtra("result", result)
            setResult(Activity.RESULT_OK,data)
        }
        finish()
    }

    private fun checkEditTextInput():Boolean {
        val input1 = numberInput1.text.toString()
        val input2 = numberInput2.text.toString()
        return !TextUtils.isEmpty(input1) && !TextUtils.isEmpty(input2)
    }

    private fun refreshResult(){
        if( checkEditTextInput()){
            val result:Int = calc()
            val resultText = getString(R.string.calc_result_text,result)
            calcResult.text = resultText
        }else{
            calcResult.text = R.string.calc_result_default.toString()
        }
    }
    private fun calc():Int {
        val input1 = numberInput1.text.toString().toInt()
        val input2 = numberInput2.text.toString().toInt()
        val operator = operatorSelector.selectedItemPosition
        return when( operator ){
            0->input1+input2
            1->input1-input2
            2->input1*input2
            3->input1/input2
            else->0
        }
    }
}

