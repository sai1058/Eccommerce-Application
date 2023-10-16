package com.example.calculatorappexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var textResult:TextView
    private val  arrayOfButton = arrayOf(R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btnZero,R.id.btnDot)
    private val arrayForOperations = arrayOf(R.id.btnPercentile,R.id.btnMultiply,R.id.btnAddition,R.id.btnSubtract,R.id.btnDivide,R.id.btnPlusMinus,R.id.btnAc)
    private val mainViewModel : MainViewModel by viewModels()
    var str = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // Log.i("oncreate", "Activity created $this, viewModel:${mainViewModel}")
         textResult = findViewById<TextView>(R.id.result)
         textResult.text = mainViewModel.str
            for (btn in arrayOfButton)
            {
                val button = findViewById<Button>(btn)
                button.setOnClickListener {
                    var btnText =(it as Button).text.toString()
                    val res = mainViewModel.arrayOfButton(btnText)
                    textResult.text = res

                }

            }
        for(btn in arrayForOperations){
            val button = findViewById<Button>(btn)
            button.setOnClickListener{
                val operationText =(it as Button).text.toString()
                mainViewModel.arrayOfOperations(operationText)
                    textResult.text = str
                }

            }
        findViewById<Button>(R.id.btnAc).setOnClickListener {
            textResult.text = ""
            mainViewModel.clear()

        }
        findViewById<Button>(R.id.btnEqual).setOnClickListener{
            if (mainViewModel.isOperation()){
                val result = mainViewModel.arrayOfResult()
                textResult.text = result
            }
        }
    }
}