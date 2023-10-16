package com.example.mynewsapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide

class DetailedNewsFragment : Fragment() {
    private val home: HomeViewModel by viewModels()

    //private val viewModel: DetailedNewsViewModel by viewModels()
    private lateinit var detailedImage: ImageView
    private lateinit var detailedTitle: TextView
    private lateinit var author: TextView
    private lateinit var publishedAt: TextView
    private lateinit var detailedDescription: TextView
    private lateinit var detailedContentTextview: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedImage = view.findViewById(R.id.detailedImageView)
        detailedTitle = view.findViewById(R.id.detailedTitleTextView)
        author = view.findViewById(R.id.detailedAuthorTextView)
        publishedAt = view.findViewById(R.id.detailedPublishedDateTextView)
        detailedDescription = view.findViewById(R.id.detailedDescriptionTextView)
        detailedContentTextview = view.findViewById(R.id.detailedContentTextView)


        detailedTitle.text = arguments?.getString("title")

        Log.i("Title is ", "${detailedTitle.text}")

        detailedDescription.text = arguments?.getString("description")

        author.text = arguments?.getString("author")
        publishedAt.text = arguments?.getString("published")

        Glide.with(view.context)
            .load(arguments?.getString("image"))
            .into(detailedImage)

        detailedContentTextview.text = arguments?.getString("content")
    }
}















//         getArticle?.let {
//             detailedTitle.text = it.title
//             detailedDescription.text = it.description
//             author.text = it.author
//             publishedAt.text = it.publishedAt
//         }
//        home._selectedArticle.observe(viewLifecycleOwner){ article ->
//
//                detailedTitle.text = article.title
//                detailedDescription.text = article.description
//                author.text = article.author
//                publishedAt.text = article.publishedAt
//
