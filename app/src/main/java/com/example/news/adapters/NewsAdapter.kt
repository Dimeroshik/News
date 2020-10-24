package com.example.news.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Model.News
import com.example.news.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_single.*
import kotlinx.android.synthetic.main.item_single.view.*

class NewsAdapter : RecyclerView. Adapter<NewsAdapter.SingleViewHolder>() {

    var items: MutableList<News> = mutableListOf()

    fun setDataset(new_items: MutableList<News>){
        items = new_items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_single, parent, false)
        return SingleViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SingleViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: News) {
            tv_title_single.text = item.title
            tv_description_single.text = item.description
        }


    }


}

//class ChatAdapter : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {
//    var items: List<ChatItem> = listOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val convertView = inflater.inflate(R.layout.item_chat_single, parent, false)
//        return SingleViewHolder(convertView)
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
//        Log.d("M_ChatAdapter", "OnBindViewHolder $position")
//        holder.bind(items[position])
//    }
//
//    fun updateData(data: List<ChatItem>) {
//        items = data
//        notifyDataSetChanged()
//    }
//
//
//    inner class SingleViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
//        LayoutContainer {
//
//        override val containerView: View?
//            get() = itemView
//
//        fun bind(item: ChatItem) {
//            if (item.avatar == null) {
//                iv_avatar_single.setInitials(item.initials)
//            } else {
//                //TODO
//            }
//
//            sv_indicator.visibility = if (item.isOnline) View.VISIBLE else View.GONE
//            with(tv_date_single) {
//                visibility = if (item.lastMessageDate != null) View.VISIBLE else View.GONE
//                text = item.lastMessageDate
//            }
//
//            with(tv_counter_single) {
//                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
//                text = item.messageCount.toString()
//            }
//
//            tv_title_single.text = item.title
//            tv_message_single.text = item.shortDescription
//
//        }
//
//
//    }
//}
