package com.example.ecommerceapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.activity.MainActivity

class PaymentDoneFragment : Fragment() {
    private lateinit var click: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click = view.findViewById(R.id.clickOk)
        click.setOnClickListener {
            val frag = HomeScreenFragment()
            (activity as MainActivity).replaceCurrentFragment(frag)
        }
    }
    companion object {
        fun newInstance() = PaymentDoneFragment()

    }
}