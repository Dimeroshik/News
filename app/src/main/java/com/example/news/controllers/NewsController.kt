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
    val baseUrl : String,
    val newsListUpdateListener: NewsListUpdateListener
) {

    fun getNews(settings : Map<String, String>) {

        var news: MutableList<News>

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder().addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client.build())
            .build()
        val newsApi = retrofit.create(NewsApi :: class.java)

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
                Log.d("M_OnResponse", "True")

                var newsJson = response.body()

                if (newsJson != null) {
                    news = newsJson.articles ?: mutableListOf()
                    if (news.size == 0)
                        Log.d("M_getNews", "news")
                    newsListUpdateListener.updateNews(news)
                    Log.d("M_getNews", "News size is: ${news.size}")

                }else {
                    news = mutableListOf()
                    Log.d("M_getNews", "newsJson == null")
                }
            }

        })
    }
}