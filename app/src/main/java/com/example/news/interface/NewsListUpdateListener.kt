package com.example.news.`interface`

import com.example.news.model.News

interface NewsListUpdateListener {
    fun updateNews(newsList: MutableList<News>)

    fun getNews()
}