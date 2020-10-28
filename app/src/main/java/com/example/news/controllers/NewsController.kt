package com.example.news.controllers

import android.util.Log
import com.example.news.Model.News
import com.example.news.Model.NewsJson
import com.example.news.`interface`.NewsListUpdateListener
import com.example.news.`interface`.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsController(
    baseUrl : String,
    val newsListUpdateListener: NewsListUpdateListener
) {
    private var newsApi: NewsApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder().addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client.build())
            .build()
         newsApi = retrofit.create(NewsApi :: class.java)
    }

    fun getNews(settings : Map<String, String>) {

        val apiKey = settings.getValue("apiKey")
        val pageSize = settings.getValue("pageSize")
        val myCall: Call<NewsJson> = newsApi.getNews(apiKey, pageSize)

        myCall.enqueue(object: Callback<NewsJson>{

            override fun onFailure(call: Call<NewsJson>, t: Throwable) {
                Log.e("NewsController", "onFailure")

            }

            override fun onResponse(
                call: Call<NewsJson>,
                response: Response<NewsJson>
            ) {
                val news: MutableList<News>
                val newsJson = response.body()

                Log.d("M_OnResponse", "True")

                if (newsJson != null) {
                    news = newsJson.articles ?: mutableListOf()
                    if (news.size == 0)
                        Log.d("M_getNews", "news")
                    newsListUpdateListener.updateNews(news)
                    Log.d("M_getNews", "News size is: ${news.size}")

                }else {
                    Log.d("M_getNews", "newsJson == null")
                }
            }

        })
    }
}