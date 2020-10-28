package com.example.news.`interface`

import com.example.news.Model.News
import com.example.news.Model.NewsJson
import retrofit2.Call
import retrofit2.http.*


interface NewsApi {
    @GET("/v2/everything?")
    fun getNewsWithMap(
        @QueryMap options: Map<String, String>
    ): Call<MutableList<News>>

    @GET("/v2/top-headlines?country=us")
    fun getNews(
        @Query("apiKey") key: String,
        @Query("pageSize") count: String
    ): Call<NewsJson>
}