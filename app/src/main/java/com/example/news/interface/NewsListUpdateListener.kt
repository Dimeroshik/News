package com.example.news.`interface`

import com.example.news.Model.News

interface NewsListUpdateListener {
    fun updateNews(newsList: MutableList<News>)

    fun getNews()
}