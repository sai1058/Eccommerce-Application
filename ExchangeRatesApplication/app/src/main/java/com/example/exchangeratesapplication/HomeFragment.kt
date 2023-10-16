package com.example.exchangeratesapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), ItemOnClick {
    private val homeViewModel: HomeFragmentViewModel by viewModels()
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var listItems = mutableListOf<Pair<String, Any>>()
    private lateinit var btnConvertRates: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnConvertRates = view.findViewById(R.id.btnConvertRates)
        editText = view.findViewById<EditText>(R.id.editText)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MyAdapter(listItems, this)
        recyclerView.adapter = adapter

        homeViewModel.fetchCurrencyData()
        homeViewModel.currencyRatesLiveData.observe(viewLifecycleOwner) {
            listItems.addAll(it)
            adapter.notifyDataSetChanged()
        }
        btnConvertRates.setOnClickListener {
            val amountText = editText.text.toString().toDouble()
            val convertedRates = homeViewModel.convertRates(amountText)
            listItems.clear()
            listItems.addAll(convertedRates)
            adapter.notifyDataSetChanged()
        }
    }
    companion object {
        fun newInstance() = HomeFragment()
    }
    override fun itemClick(index: Int) {
        Log.i("clicked items are", "$index")
    }
}