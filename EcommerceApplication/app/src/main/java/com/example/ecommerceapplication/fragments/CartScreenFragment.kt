package com.example.ecommerceapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.data.CartSharedPreferencesManager
import com.example.ecommerceapplication.data.ItemOnClick
import com.example.ecommerceapplication.recyclerView.CartScreenAdapter
import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.viewModels.CartScreenViewModel

class CartScreenFragment : Fragment(), ItemOnClick {
    private lateinit var btnCheckoutList: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartScreenAdapter
    private var checkOutListItems: MutableList<Product> = mutableListOf()
    private lateinit var backArrow:ImageView
    private lateinit var cartViewModel: CartScreenViewModel
    private lateinit var cartSharedPreferencesManager: CartSharedPreferencesManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCheckoutList = view.findViewById(R.id.btnCheckout)
        recyclerView = view.findViewById(R.id.recyclerViewCart)
        backArrow = view.findViewById(R.id.backArrow)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val cartScreenViewModel = (requireActivity() as MainActivity).cartScreen

        cartSharedPreferencesManager = CartSharedPreferencesManager(requireContext())
        checkOutListItems.clear()
        checkOutListItems.addAll(cartSharedPreferencesManager.getCart())
        cartSharedPreferencesManager.saveCart(checkOutListItems)


        if (cartScreenViewModel != null) {
            checkOutListItems.clear()
            checkOutListItems.addAll(cartScreenViewModel.selectedProducts)

        }
        adapter = CartScreenAdapter(checkOutListItems, this)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()

        btnCheckoutList.setOnClickListener {
            clearAndSaveCart()
            (activity as FragmentCommunicator).communicator(FakePaymentFragment.newInstance())
        }
        backArrow.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    companion object {
        fun newInstance() = CartScreenFragment()
    }

    override fun itemClick(index: Int) {
        cartViewModel = (requireActivity() as MainActivity).cartScreen!!
        val product = adapter.getItem(index)
        if (product.count > 1) {
            // Decrease the count
            product.count--
        } else {
            // Remove the item from the cart
            adapter.removeItem(index)
            cartViewModel.removeItem(index)
        }
        adapter.notifyDataSetChanged()
    }
    private fun clearAndSaveCart() {
        // Clear the cart
        checkOutListItems.clear()
        adapter.notifyDataSetChanged()
        // Save the cleared cart to SharedPreferences
        cartSharedPreferencesManager.saveCart(checkOutListItems)
    }
}