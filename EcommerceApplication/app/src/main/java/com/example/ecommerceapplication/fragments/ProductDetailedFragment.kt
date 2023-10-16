package com.example.ecommerceapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.activity.MainActivity
import com.example.ecommerceapplication.viewModels.HomeScreenViewModel

class ProductDetailedFragment : Fragment() {
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private lateinit var titleView: TextView
    private lateinit var imageView: ImageView
    private lateinit var ratingView: TextView
    private lateinit var cartBtn: Button
    private lateinit var descriptionView: TextView
    private lateinit var imageView2: ImageView
    private lateinit var price: TextView

    companion object {
        fun newInstance() = ProductDetailedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleView = view.findViewById(R.id.textView)
        imageView = view.findViewById(R.id.productImage)
        ratingView = view.findViewById(R.id.textView2)
        descriptionView = view.findViewById(R.id.textView3)
        imageView2 = view.findViewById(R.id.imageView2)
        price = view.findViewById(R.id.textViewPrice)
        val cartScreenViewModel = (requireActivity() as MainActivity).cartScreen

        cartBtn = view.findViewById(R.id.btnCart)
        cartBtn.text = "${cartScreenViewModel?.selectedProducts?.size}"

        val indexNum = arguments?.getString("index")?.toInt()
        titleView.text = arguments?.getString("title")
        Glide.with(view.context)
            .load(arguments?.getString("productImage"))
            .into(imageView)
        ratingView.text = "Rating: " + arguments?.getString("rate")
        descriptionView.text = arguments?.getString("description")
        price.text = "Cost:" + arguments?.getString("price") + "$"

        view.findViewById<Button?>(R.id.btnCart).setOnClickListener {
            val frag = CartScreenFragment.newInstance()
            (activity as MainActivity).replaceCurrentFragment(frag)
        }
        homeScreenViewModel.fetchProductWithId(indexNum!! + 1)
        view.findViewById<Button?>(R.id.btnAddToCart).setOnClickListener {

                homeScreenViewModel.productDetailsWithId.removeObservers(viewLifecycleOwner)
                homeScreenViewModel.productDetailsWithId.observe(viewLifecycleOwner) { listProduct->
                    (requireActivity() as MainActivity).cartScreen?.addToCart(listProduct[0])
                    Log.i("selected products","$it")
                    cartBtn.text = "${cartScreenViewModel?.selectedProducts?.size}"
                }
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
        }
        imageView2.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

