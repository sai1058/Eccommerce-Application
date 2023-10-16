package com.example.ecommerceapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.data.ItemOnClick
import com.example.ecommerceapplication.recyclerView.MyAdapter
import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.viewModels.HomeScreenViewModel

class HomeScreenFragment : Fragment(), ItemOnClick {
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private lateinit var btnLogout:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var listItems: MutableList<Product> = mutableListOf()
    private lateinit var btnCart:Button
    private lateinit var progressBar: ProgressBar  // Add ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCart = view.findViewById(R.id.btnGoToCart)
        btnLogout = view.findViewById(R.id.buttonLogout)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)  // Initialize ProgressBar

        val cartCount = (requireActivity() as MainActivity).cartScreen
        btnCart.text = "${cartCount?.selectedProducts?.size}"

        val layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.layoutManager = layoutManager

        adapter = MyAdapter(listItems, this)
        recyclerView.adapter = adapter

        progressBar.visibility = View.VISIBLE

        homeScreenViewModel.fetchProduct()
        homeScreenViewModel.productDetails.observe(viewLifecycleOwner){data ->
                adapter.updateData(data)
                progressBar.visibility = View.GONE

        }
        btnCart.setOnClickListener {
            val btnFrag = CartScreenFragment.newInstance()
            (activity as MainActivity).replaceCurrentFragment(btnFrag)
            btnCart.text = "${cartCount?.selectedProducts?.size}"
        }
        btnLogout.setOnClickListener {
            (activity as FragmentCommunicator).communicator(LoginFragment.newInstance())
        }
    }
    override fun itemClick(index: Int) {
        val selectedProduct = adapter.getItem(index)

        val bundle = Bundle()
        bundle.putString("index", index.toString())
        bundle.putString("title",selectedProduct.title)
        bundle.putString("id", selectedProduct.id.toString())
        bundle.putString("productImage",selectedProduct.image)
        bundle.putString("price", selectedProduct.price.toString())
        bundle.putString("description", selectedProduct.description)
        bundle.putString("rate", selectedProduct.rating.rate.toString())
        bundle.putString("count", selectedProduct.rating.count.toString())

        (activity as FragmentCommunicator).DetailedCommunicator(bundle)
    }
    companion object {
        fun newInstance() = HomeScreenFragment()
    }
}