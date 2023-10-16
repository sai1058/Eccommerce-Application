package com.example.calculatorappexample

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var str =""
    var selectedOp = ""
    var inputNum : Double = 0.0
    fun arrayOfButton(btnText: String):String{
        if (btnText == ".") {
            if(!str.contains(".")) {
                str += btnText

            }
        }
        else{
            str += btnText

        }
        return str
    }
    fun arrayOfOperations(operationText: String){
        inputNum = str.toDouble()
        str = ""
        selectedOp =operationText

    }
    fun clear(){
        str = ""
        inputNum = 0.0
        selectedOp = ""
    }
    fun arrayOfResult():String {
        var res = ""
        val inputNumberTwo = str.toDouble()
        when(selectedOp) {
            "+" -> {
                 res = addition(inputNum, inputNumberTwo).toString()
                str = ""
                selectedOp = ""

            }
            "-" -> {
                res =subtract(inputNum, inputNumberTwo).toString()
                str = ""
                selectedOp = ""
            }
            "*" -> {
                res = multiply(inputNum, inputNumberTwo).toString()
                str = ""
                selectedOp = ""
            }
            "/" -> {
                res = divide(inputNum, inputNumberTwo).toString()
                str = ""
                selectedOp = ""
            }
            "%" -> {
                res = percentile(inputNum, inputNumberTwo).toString()
                str = ""
                selectedOp= ""
            }
        }
        str = res
        return res
    }
    fun isOperation():Boolean{
        return str.isNotEmpty() && selectedOp.isNotEmpty()
    }

    fun addition( x : Double, y : Double):Double{
        return x + y
    }
    fun subtract( x : Double, y : Double):Double{
        return x - y
    }
    fun multiply( x : Double, y : Double):Double{
        return x * y
    }
    fun divide( x : Double, y : Double):Double{
        if (y != 0.0) {
            return x / y
        } else {
            return Double.POSITIVE_INFINITY
        }
    }
    fun percentile( x : Double, y : Double):Double{
        return x % y
    }
}