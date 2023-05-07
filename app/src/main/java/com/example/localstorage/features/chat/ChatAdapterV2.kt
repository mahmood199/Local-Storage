package com.example.localstorage.features.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.localstorage.R
import com.example.localstorage.databinding.ItemChatUserQueryBinding
import com.example.localstorage.databinding.ItemChatWordMeaningResponseBinding

class ChatAdapterV2 : ListAdapter<ChatUIItem, RecyclerView.ViewHolder>(ChatDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_chat_user_query -> {
                UserQueryViewHolder2(
                    ItemChatUserQueryBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        )
                    )
                )
            }
            R.layout.item_chat_word_meaning_response -> {
                WordMeaningViewHolder2(
                    ItemChatWordMeaningResponseBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        )
                    )
                )
            }
            else -> {
                throw Exception("Illegal view")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ChatUIItem.ServerResponse -> (holder as WordMeaningViewHolder2).bind(item)
            is ChatUIItem.UserQuery -> (holder as UserQueryViewHolder2).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ChatUIItem.ServerResponse -> R.layout.item_chat_word_meaning_response
            is ChatUIItem.UserQuery -> R.layout.item_chat_user_query
        }
    }


    inner class UserQueryViewHolder2(private val binding: ItemChatUserQueryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatUIItem.UserQuery) {
            binding.tvMessage.text = item.message
        }

    }

    inner class WordMeaningViewHolder2(private val binding: ItemChatWordMeaningResponseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatUIItem.ServerResponse) {
            binding.tvMeaning.text = item.message
        }

    }

}

class ChatDiffCallBack : DiffUtil.ItemCallback<ChatUIItem>() {

    companion object {
        const val TAG = "TaskDiffCallBack"
    }

    override fun areItemsTheSame(oldItem: ChatUIItem, newItem: ChatUIItem): Boolean {
        Log.d(TAG, Thread.currentThread().name)
        return oldItem.id == newItem.id;
    }

    override fun areContentsTheSame(oldItem: ChatUIItem, newItem: ChatUIItem): Boolean {
        Log.d(TAG, Thread.currentThread().name)
        return oldItem == newItem
    }

}