package com.example.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.Model.News
import com.example.news.R
import com.example.news.`interface`.NewsListUpdateListener
import com.example.news.`interface`.OnItemClickListener
import com.example.news.adapters.NewsAdapter
import com.example.news.controllers.NewsController
import kotlinx.android.synthetic.main.news_list_fragment.*

class NewsListFragment : Fragment(), NewsListUpdateListener {

    companion object {
        private var currentListSize:Int = 10
        private var countAdditionalNewsList = 10
    }

    private val baseUrl: String = "https://newsapi.org"
    private val API_KEY = "13d03285ccb940fc8ee06aecafa28641"
    private var settings: Map<String, String> = mapOf(
        "pageSize" to currentListSize.toString(),
        "country" to "us",
        "apiKey" to API_KEY
    )



    private lateinit var nc: NewsController
    private lateinit var rvAdapter: NewsAdapter
    private lateinit var mainListener : OnItemClickListener

    override fun onAttach(context: Context) {
        if (context is OnItemClickListener)
            mainListener = context

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.news_list_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val llm = LinearLayoutManager(activity)

        nc = NewsController(baseUrl, this)
        nc.getNews(settings)
        rvAdapter = NewsAdapter()
        rvAdapter.setClickListener(mainListener)
        NewsAdapter.setNewsListUpdateListener(rvAdapter, this)

        rv_list.layoutManager = llm
        rv_list.adapter = rvAdapter

        super.onStart()
    }

    override fun updateNews(newsList: MutableList<News>){
        rvAdapter.updateDataSet(newsList)
    }

    override fun getNews(){
        currentListSize += countAdditionalNewsList
        settings = mapOf(
            "pageSize" to currentListSize.toString(),
            "country" to "us",
            "apiKey" to API_KEY
        )

        nc.getNews(settings)
    }


}