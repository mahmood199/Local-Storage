package com.example.localstorage.features.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.localstorage.R
import com.example.localstorage.databinding.ItemChatUserQueryBinding
import com.example.localstorage.databinding.ItemChatWordMeaningResponseBinding

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ChatUIItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_chat_user_query -> {
                UserQueryViewHolder(ItemChatUserQueryBinding.inflate(LayoutInflater.from(parent.context)))
            }
            R.layout.item_chat_word_meaning_response -> {
                WordMeaningViewHolder(
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

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = items[position]) {
            is ChatUIItem.ServerResponse -> (holder as WordMeaningViewHolder).bind(item)
            is ChatUIItem.UserQuery -> (holder as UserQueryViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ChatUIItem.ServerResponse -> R.layout.item_chat_word_meaning_response
            is ChatUIItem.UserQuery -> R.layout.item_chat_user_query
        }
    }

    fun addItem(list: List<ChatUIItem>) {
        val originalSize = items.size
        items.addAll(list)
        notifyItemRangeChanged(originalSize, items.size)
    }




    inner class UserQueryViewHolder(private val binding: ItemChatUserQueryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatUIItem.UserQuery) {
            binding.tvMessage.text = item.message
        }

    }

    inner class WordMeaningViewHolder(private val binding: ItemChatWordMeaningResponseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatUIItem.ServerResponse) {
            binding.tvMeaning.text = item.message
        }

    }


}