package com.example.arthcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        one.setOnClickListener{appendInExpression("1",true)}
        two.setOnClickListener{appendInExpression("2",true)}
        three.setOnClickListener{appendInExpression("3",true)}
        four.setOnClickListener{appendInExpression("4",true)}
        five.setOnClickListener{appendInExpression("5",true)}
        six.setOnClickListener{appendInExpression("6",true)}
        seven.setOnClickListener{appendInExpression("7",true)}
        eight.setOnClickListener{appendInExpression("8",true)}
        nine.setOnClickListener{appendInExpression("9",true)}
        zero.setOnClickListener{appendInExpression("0",true)}
        point.setOnClickListener{appendInExpression(".",true)}

        add.setOnClickListener{appendInExpression("+",false)}
        sub.setOnClickListener{appendInExpression("-",false)}
        mul.setOnClickListener{appendInExpression("*",false)}
        div.setOnClickListener{appendInExpression("/",false)}
        rem.setOnClickListener{appendInExpression("%",false)}
        exp.setOnClickListener{appendInExpression("^",false)}

        allclear.setOnClickListener{
            tvexpression.text=""
            tvresult.text=""
        }

        backspace.setOnClickListener{
            val string = tvexpression.text.toString()
            if(string.isNotEmpty()){
                tvexpression.text = string.substring(0,string.length-1)
            }
            tvresult.text = ""
        }

        equal.setOnClickListener{
            try {
                val expression = ExpressionBuilder(tvexpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvresult.text = longResult.toString()
                else
                    tvresult.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }
    }


    fun appendInExpression(s: String, canClear: Boolean) {
        if(tvresult.text.isNotEmpty()){
            tvexpression.text = ""
        }

        if (canClear) {
            tvresult.text = ""
            tvexpression.append(s)
        } else {
            tvexpression.append(tvresult.text)
            tvexpression.append(s)
            tvresult.text = ""
        }
    }
}