package com.example.mynewsapplication

import android.os.Bundle
import android.os.Parcelable
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

class HomeFragment : Fragment(), ItemOnClick {
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var textView: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var listItems: MutableList<Article> = mutableListOf()
    private lateinit var btnSearch: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch = view.findViewById(R.id.btn_search)
        textView = view.findViewById(R.id.enter_search)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MyAdapter(listItems, this)
        recyclerView.adapter = adapter

        homeViewModel.fetchNewsData("India")
        homeViewModel.newsArticle.observe(viewLifecycleOwner) { newsResponse ->
            newsResponse?.articles?.let { articles ->
                adapter.updateData(articles)
                adapter.notifyDataSetChanged()
            }
        }
        btnSearch.setOnClickListener {
            val query = textView.text.toString()
            homeViewModel.fetchNewsData(query)
        }
    }
    override fun itemClick(index: Int) {
        val selectedArticle = adapter.getItem(index)

        val bundle = Bundle()
        bundle.putString("title", selectedArticle.title)
        bundle.putString("image", selectedArticle.urlToImage)
        bundle.putString("description", selectedArticle.description)
        bundle.putString("author", selectedArticle.author)
        bundle.putString("published", selectedArticle.publishedAt)
        bundle.putString("content",selectedArticle.content)

        val detailedNewsFragment = DetailedNewsFragment()
        detailedNewsFragment.arguments = bundle

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, detailedNewsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    companion object {
        fun newInstance() = HomeFragment()
    }
}