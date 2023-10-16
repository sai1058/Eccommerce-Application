package com.example.ecommerceapplication.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.ecommerceapplication.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var userNameInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var userName: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userNameInputLayout = view.findViewById(R.id.usernameInputLayout)
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout)
        userName = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        loginButton = view.findViewById(R.id.loginBtn)
        signUpButton = view.findViewById(R.id.signUpBtn)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        userName.addTextChangedListener {
            userNameInputLayout.error = null
            updateLoginButtonStateChange()
        }
        password.addTextChangedListener {
            passwordInputLayout.error = null
            updateLoginButtonStateChange()
        }

        loginButton.setOnClickListener {
            performLogin()
        }

        signUpButton.setOnClickListener {
            (activity as FragmentCommunicator).communicator(SignUpFragment.newInstance())
        }
    }

    private fun enableLoginButton(username: String, password: String): Boolean {
        userNameInputLayout.error = null
        passwordInputLayout.error = null
        var isValid = false

        if (isValidCredentials(username, password)) {
            isValid = true
        } else {
            if (username.isEmpty()) {
                userNameInputLayout.error = "Username is required"
            }

            if (password.isEmpty()) {
                passwordInputLayout.error = "Password is required"
            }
        }

        return isValid
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        val storedUserName = sharedPreferences.getString("name", "")
        val storedPassword = sharedPreferences.getString("password", "")

        return username == storedUserName && password == storedPassword
    }

    private fun updateLoginButtonStateChange() {
        val username = userName.text.toString()
        val userPassword = password.text.toString()

        loginButton.isEnabled = enableLoginButton(username, userPassword)
    }

    private fun performLogin() {
        val username = userName.text.toString()
        val userPassword = password.text.toString()

        if (isValidCredentials(username, userPassword)) {
            (activity as FragmentCommunicator).communicator(MainHomeFragment.newInstance(username))
        } else {
            Toast.makeText(context, "Enter Valid Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}