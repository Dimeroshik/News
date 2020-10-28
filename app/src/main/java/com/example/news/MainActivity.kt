package com.example.news

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.news.Extensions.isInternetAvailable
import com.example.news.Model.News
import com.example.news.`interface`.OnItemClickListener
import com.example.news.adapters.NewsAdapter
import com.example.news.fragments.NewsDetailsFragment
import com.example.news.fragments.NewsListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var newsList: NewsListFragment
    lateinit var newsDetails: NewsDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isInternetAvailable(this)) {
            Log.d("M_isInternetAwailable", "true")

            newsList = NewsListFragment()
            newsDetails = NewsDetailsFragment()

            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, newsList)
                .commit()
            iv_navigation_back.visibility = View.GONE
            iv_navigation_back.setOnClickListener{
                supportFragmentManager.popBackStack()
                iv_navigation_back.visibility = View.GONE
            }


        } else {
            Toast.makeText(applicationContext, "Интернета нет", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openNewsDetails(news: News){
        val newsBundle = Bundle()
        newsBundle.putParcelable("args", news)
        newsDetails = NewsDetailsFragment.getListNews(newsBundle)

        iv_navigation_back.visibility = View.VISIBLE

        supportFragmentManager
            .beginTransaction()
            .hide(newsList)
            .add(R.id.fragment_container, newsDetails)
            .addToBackStack(null)
            .commit()


    }

    override fun onItemClick(newsItem: News) {
        openNewsDetails(newsItem)
    }
}