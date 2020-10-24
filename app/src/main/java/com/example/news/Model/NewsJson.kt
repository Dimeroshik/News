package com.example.news.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class NewsJson {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null

    @SerializedName("articles")
    @Expose
    var articles: MutableList<News>? = null

}