package com.example.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.news.Model.News
import com.example.news.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_details_fragment.*
import java.lang.Exception

class NewsDetailsFragment: Fragment() {

    companion object {
        fun getInstance(newsBundle: Bundle): NewsDetailsFragment{
            val newFragment = NewsDetailsFragment()
            newFragment.arguments = newsBundle
            return newFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_news_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val news = arguments?.getParcelable<News>("args") ?: News()

        setData(news_details_title, news.title)
        setData(news_details_image, news.urlToImage)
        setData(news_details_content, news.content)
        setData(news_details_author, news.author)
        setData(news_details_link_original, news.url)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setData (view: View, args: String?){
        when(view){
            is TextView ->
                if(args != null) view.text = args
                else view.visibility = View.GONE
            is ImageView ->
                if(args != null) {
                    Picasso.get()
                        .load(args)
                        .error(R.drawable.error_humster)
                        .into(news_details_image, object : Callback {
                            override fun onSuccess() {

                            }

                            override fun onError(e: Exception?) {
                                Log.e("M_bind_details", "load image error")
                            }
                        })
                } else {
                    view.visibility = View.GONE
                }
        }
    }
}