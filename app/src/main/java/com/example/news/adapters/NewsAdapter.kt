package com.example.news.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.model.News
import com.example.news.R
import com.example.news.`interface`.NewsListUpdateListener
import com.example.news.`interface`.OnItemClickListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news_single.*
import java.lang.Exception

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.SingleViewHolder>() {

    var items: MutableList<News> = mutableListOf()
    lateinit var mListener : OnItemClickListener
    lateinit var newsListUpdateListener: NewsListUpdateListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_news_single, parent, false)
        return SingleViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        holder.bind(items[position])
        holder.setClickListener(mListener, items[position])
        if (position == itemCount - 1) {
            newsListUpdateListener.getNews()
            Log.d("M_NewsAdapter", "items update")
        }
    }

    fun updateDataSet(new_items: MutableList<News>){

        val diffCallback = object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean = items[oldPos].url == new_items[newPos].url

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean = items[oldPos].hashCode() == new_items[newPos].hashCode()

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = new_items.size
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = new_items
        diffResult.dispatchUpdatesTo(this)
    }

    fun setClickListener(mainListener: OnItemClickListener) {
       mListener = mainListener
    }

    inner class SingleViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: News) {
            tv_title_single.text = item.title
            tv_description_single.text = item.description

            val image = iv_image_single
            if(item.urlToImage != null) {
            Picasso.get()
                .load(item.urlToImage)
                .error(R.drawable.error_humster)
                .into(image, object: Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {
                        Log.e("M_bind", "load image error")
                    }
                })
             } else {
                iv_image_single.visibility = View.GONE
             }
        }

        fun setClickListener(mListener: OnItemClickListener, news_item: News) {
            itemView.setOnClickListener{
                mListener.onItemClick(news_item)
            }
        }
    }

    companion object {
        fun setNewsListUpdateListener(newsAdapter: NewsAdapter, newsListFragment: NewsListUpdateListener) {
                newsAdapter.newsListUpdateListener = newsListFragment
        }
    }
}
