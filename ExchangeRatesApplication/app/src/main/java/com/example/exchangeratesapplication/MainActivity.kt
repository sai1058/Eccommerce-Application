package com.example.exchangeratesapplication

import MyData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() , InterFragmentCommunicator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(LoginFragment.newInstance())
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.toString())
        transaction.commit()
    }
    override fun communicator(instance: Fragment, data: MyData) {
        if(instance is HomeFragment) {
            replaceFragment(instance)
        }
    }
}