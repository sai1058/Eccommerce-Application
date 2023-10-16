package com.example.ecommerceapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ecommerceapplication.R

class MainHomeFragment : Fragment() {
    private lateinit var userText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userText = view.findViewById(R.id.userText)

        val username = arguments?.getString("username")
        userText.text = "Welcome  " + "$username"
        view.findViewById<Button>(R.id.btnStart).setOnClickListener {
            (activity as FragmentCommunicator).communicator(HomeScreenFragment.newInstance())
        }
    }

    companion object {
        fun newInstance(username: String): MainHomeFragment {
            val bundle = Bundle()
            bundle.putString("username", username)
            val fragment = MainHomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}