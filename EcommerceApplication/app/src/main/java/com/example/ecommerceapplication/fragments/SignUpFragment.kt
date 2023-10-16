package com.example.ecommerceapplication.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.data.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : Fragment() {
    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var addressInputLayout: TextInputLayout
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var name: TextInputEditText
    private lateinit var address: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnSignIn: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameInputLayout = view.findViewById(R.id.nameInputLayout)
        addressInputLayout = view.findViewById(R.id.addressInputLayout)
        emailInputLayout = view.findViewById(R.id.emailInputLayout)
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout)
        name = view.findViewById(R.id.name)
        address = view.findViewById(R.id.address)
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        btnSignIn = view.findViewById(R.id.signIn)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        name.addTextChangedListener {
            nameInputLayout.error = null
            updateSignUpButtonStateChange()
        }

        address.addTextChangedListener {
            addressInputLayout.error = null
            updateSignUpButtonStateChange()
        }

        email.addTextChangedListener {
            emailInputLayout.error = null
            updateSignUpButtonStateChange()
        }

        password.addTextChangedListener {
            passwordInputLayout.error = null
            updateSignUpButtonStateChange()
        }

        btnSignIn.setOnClickListener {
            val username = name.text.toString()
            val userAddress = address.text.toString()
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if (validateUserInput(username, userAddress, userEmail, userPassword)) {
                val user = User(username, userAddress, userEmail, userPassword)
                saveUser(user)
                (activity as FragmentCommunicator).communicator(LoginFragment.newInstance())
            }
        }
    }

    private fun validateUserInput(
        username: String,
        address: String,
        email: String,
        password: String
    ): Boolean {
        var isValid = true

        if (username.length <= 5) {
            nameInputLayout.error = "Username should be at least 6 characters"
            isValid = false
        }

        if (address.length <= 10) {
            addressInputLayout.error = "Address should be at least 11 characters"
            isValid = false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@gmail.com"
        if (!email.matches(emailPattern.toRegex())) {
            emailInputLayout.error = "Enter a valid Gmail address"
            isValid = false
        }

        val hasDigit = password.any { it.isDigit() }
        val hasLetter = password.any { it.isLetter() }

        if (!hasDigit || !hasLetter) {
            passwordInputLayout.error = "Password should contain both letters and numbers"
            isValid = false
        }

        return isValid
    }
    private fun updateSignUpButtonStateChange() {
        val username = name.text.toString()
        val userAddress = address.text.toString()
        val userEmail = email.text.toString()
        val userPassword = password.text.toString()

        btnSignIn.isEnabled = validateUserInput(username, userAddress, userEmail, userPassword)
    }
    private fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString("name", user.name)
        editor.putString("address", user.address)
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.apply()
    }
    companion object {
        fun newInstance() = SignUpFragment()
    }
}