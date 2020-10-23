package com.example.news.common

import com.example.news.Model.News
import com.example.news.Retrofit.RetrofitClient
import com.example.news.`interface`.NewsApi


object Common {
    private val BASE_URL = "https://newsapi.org/"
    val retrofitService: NewsApi
        get() = RetrofitClient.getClient(BASE_URL).create(NewsApi::class.java)
}