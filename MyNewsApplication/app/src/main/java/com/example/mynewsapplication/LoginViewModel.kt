package com.example.mynewsapplication

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _userName: String? = null
    private var _password: String? = null

    fun setData(name: String?, password: String?) {
        _userName = name
        _password = password

    }
    fun getData(): String? {
        return _userName
    }

    fun isValid(): Boolean {
        return _userName == "Saikumar" && _password == "h1234"
    }
}