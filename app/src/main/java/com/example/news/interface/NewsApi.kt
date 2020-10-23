package com.example.news.`interface`

import com.example.news.Model.News
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.QueryMap


interface NewsApi {
    @GET("v2/everything?")
    fun getNews(
        @QueryMap options: Map<String, String>
    ): Call<MutableList<News>>
}