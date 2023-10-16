package com.example.exchangeratesapplication

import MyData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(){
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private val loginFragmentViewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.btn_login)

        login.setOnClickListener {
            val name = username.text.toString()
            val password = password.text.toString()
            loginFragmentViewModel.setData(name, password)
            val data = MyData(name, password)
            if (activity is InterFragmentCommunicator && loginFragmentViewModel.isValid()) {

                (activity as InterFragmentCommunicator).communicator(HomeFragment.newInstance(), data)
            }
            else{
                Toast.makeText(context, "Data is Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = LoginFragment()

    }
}