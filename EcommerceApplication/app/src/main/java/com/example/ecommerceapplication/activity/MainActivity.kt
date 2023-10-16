package com.example.ecommerceapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapplication.fragments.ProductDetailedFragment
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.fragments.FragmentCommunicator
import com.example.ecommerceapplication.fragments.LoginFragment
import com.example.ecommerceapplication.fragments.MainHomeFragment
import com.example.ecommerceapplication.viewModels.CartScreenViewModel

class MainActivity : AppCompatActivity(),FragmentCommunicator {
    var cartScreen : CartScreenViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        cartScreen = ViewModelProvider(this)[CartScreenViewModel::class.java]
        replaceCurrentFragment(LoginFragment.newInstance())
    }
    fun replaceCurrentFragment(frag : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_main, frag)
        transaction.addToBackStack(null)
        transaction.commit()

    }
    override fun communicator(instance: Fragment) {
        replaceCurrentFragment(instance)
    }
    override fun DetailedCommunicator(bundle: Bundle) {
        val detailedProductFragment = ProductDetailedFragment()
        detailedProductFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, detailedProductFragment)
            .addToBackStack(null)
            .commit()
    }
}
//class MainActivity : AppCompatActivity(), FragmentCommunicator {
//    var cartScreen: CartScreenViewModel? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//        cartScreen = ViewModelProvider(this)[CartScreenViewModel::class.java]
//        replaceCurrentFragment(LoginFragment.newInstance())
//    }
//
//    fun replaceCurrentFragment(fragment: Fragment) {
//        performFragmentTransaction { transaction ->
//            transaction.replace(R.id.fragment_container_main, fragment)
//            transaction.addToBackStack(null)
//        }
//    }
//
//    override fun communicator(fragment: Fragment) {
//        replaceCurrentFragment(fragment)
//    }
//
//    override fun DetailedCommunicator(bundle: Bundle) {
//        val detailedProductFragment = ProductDetailedFragment()
//        detailedProductFragment.arguments = bundle
//        performFragmentTransaction { transaction ->
//            transaction.replace(R.id.fragment_container_main, detailedProductFragment)
//            transaction.addToBackStack(null)
//        }
//    }
//
//    private fun performFragmentTransaction(transactionBlock: (FragmentTransaction) -> Unit) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transactionBlock(transaction)
//        transaction.commit()
//    }
//}

