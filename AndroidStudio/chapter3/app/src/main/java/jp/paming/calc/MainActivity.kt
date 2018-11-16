package jp.paming.calc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,TextWatcher, View.OnClickListener {

    // TODO 遅延初期化
    val REQUEST_CODE_ANOTHER_CALC_1 = 1
    val REQUEST_CODE_ANOTHER_CALC_2 = 2

    // ライフサイクル
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        numberInput1.addTextChangedListener(this)
        numberInput2.addTextChangedListener(this)

        // TODO コールバックに変更できる
        calcButton1.setOnClickListener(this)
        calcButton2.setOnClickListener(this)
        nextButton.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.extras ?: return
        if( !data?.extras.containsKey("result") ) {
            return
        }
        val result = data.extras.getInt("result")

        if( resultCode !=RESULT_OK) {
            return
        }


        when(requestCode){
            REQUEST_CODE_ANOTHER_CALC_1->{
                numberInput1.setText(result.toString())
            }
            REQUEST_CODE_ANOTHER_CALC_2->{
                numberInput2.setText(result.toString())
            }
        }

        refreshResult()
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
        when(v?.id){
            R.id.calcButton1 -> {
                var intent = Intent(this, AnotherCalcActivity::class.java)
                startActivityForResult(intent,REQUEST_CODE_ANOTHER_CALC_1)
            }
            R.id.calcButton2 -> {
                var intent = Intent(this, AnotherCalcActivity::class.java)
                startActivityForResult(intent,REQUEST_CODE_ANOTHER_CALC_2)
            }
            R.id.nextButton -> {
                if( checkEditTextInput()){
                    val result = calc()
                    numberInput1.setText(result.toString())
                    refreshResult()
                }
            }
        }
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
