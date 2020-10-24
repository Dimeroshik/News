package com.example.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Extensions.isInternetAvailable
import com.example.news.Model.News
import com.example.news.adapters.NewsAdapter
import com.example.news.controllers.NewsController
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val baseUrl: String = "https://newsapi.org"
    private val API_KEY = "13d03285ccb940fc8ee06aecafa28641"
    private var settings: Map<String, String> = mapOf(
        "country" to "us",
        "apiKey" to API_KEY
            )
    lateinit var newsList: MutableList<News>

    private val rvAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llm = LinearLayoutManager(this)

        rv_list.layoutManager = llm


        rv_list.adapter = rvAdapter


        if (isInternetAvailable(this)) {
            Log.d("M_isInternetAwailable", "true")

            val nc = NewsController(baseUrl, this)
            newsList = nc.getNews(settings)


        }


    }


    fun updateNews(newsList: MutableList<News>){
        rvAdapter.setDataset(newsList)
    }


}