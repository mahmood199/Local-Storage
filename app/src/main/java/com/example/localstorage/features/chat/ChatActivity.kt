package com.example.localstorage.features.chat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.localstorage.LocalStorageApplication
import com.example.localstorage.data.local.model.ChatType
import com.example.localstorage.databinding.ActivityChatBinding
import com.example.localstorage.extension.hideKeyboard
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(
            (application as LocalStorageApplication).database.chatDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            fab.setOnClickListener {
                checkText()
            }
            rvChat.adapter = ChatAdapterV2()
            scrollToLatestItemPosition()
        }

        lifecycleScope.launch {
            viewModel.getAllChats().collect { list ->
                (binding.rvChat.adapter as ChatAdapterV2).submitList(
                    list.map {
                        if (it.chatType == ChatType.FromServer)
                            ChatUIItem.ServerResponse(it.text, it.id.toInt(), it.imageUrl.toString())
                        else
                            ChatUIItem.UserQuery(it.text, it.id.toInt())
                    }
                )
                scrollToLatestItemPosition()
            }
        }
    }

    private fun scrollToLatestItemPosition() {
        binding.rvChat.scrollToPosition((binding.rvChat.adapter as ChatAdapterV2).itemCount - 1)
    }

    private fun checkText() {
        binding.apply {
            if (etQuery.text.isNullOrEmpty()) {
                Toast.makeText(this@ChatActivity, "Enter something", Toast.LENGTH_SHORT).show()
            } else {
                hideKeyboard(binding.root)
                viewModel.insertChatItem(etQuery.text.toString())
                etQuery.setText("")
            }
        }
    }
}