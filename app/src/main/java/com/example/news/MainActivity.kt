package com.example.news

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import com.example.news.Extensions.isInternetAvailable
import com.example.news.Model.News
import com.example.news.Model.NewsController

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val baseUrl: String = "https://newsapi.org/"
    private val API_KEY = "13d03285ccb940fc8ee06aecafa28641"
    private var settings: Map<String, String> = mapOf(
            "apiKey" to API_KEY,
            "country" to "us")
    lateinit var newsList: MutableList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (isInternetAvailable(this)) {
            Log.d("M_isInternetAwailable", "true")
            val nc: NewsController = NewsController(baseUrl)


            newsList = nc.getNews(settings)
            Log.d("M_Activity", "${newsList.size}")

            val stringBuilder = StringBuilder()

            for (news in newsList){
                with(stringBuilder){
                    append(news.author)
                    append(news.title)
                    append("--------")
                }
            }
            print(stringBuilder)
            Log.d("M_isSetText", "true")
            tv_main.text = stringBuilder
        }


    }




}