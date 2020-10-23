package com.example.news.Model

import android.util.Log
import com.example.news.`interface`.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP

class NewsController(
    val baseUrl : String
) {


    fun getNews(settings : Map<String, String>) : MutableList<News> {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client = OkHttpClient.Builder().addInterceptor(interceptor)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        val newsApi = retrofit.create(NewsApi :: class.java)

        var news: MutableList<News> = mutableListOf()

        val myCall: Call<MutableList<News>> = newsApi.getNews(settings)

        myCall.enqueue(object: Callback<MutableList<News>>{
            override fun onFailure(call: Call<MutableList<News>>, t: Throwable) {
                Log.e("NewsController", "onFailure")

            }

            override fun onResponse(
                call: Call<MutableList<News>>,
                response: Response<MutableList<News>>
            ) {
                Log.d("M_OnResponse", "True")
                news = response.body() ?: mutableListOf(News("nothing"))
            }

        })

        return news
    }
}