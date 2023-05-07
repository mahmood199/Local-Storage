package com.example.localstorage.features.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.localstorage.LocalStorageApplication
import com.example.localstorage.data.local.model.ChatType
import com.example.localstorage.databinding.ActivityChatBinding
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
            rvChat.adapter = ChatAdapter()
        }

        lifecycleScope.launch {
            viewModel.getAllChats().collect { list ->
                (binding.rvChat.adapter as ChatAdapter).addItem(
                    list.map {
                        if (it.chatType == ChatType.FromServer)
                            ChatUIItem.ServerResponse(it.text)
                        else
                            ChatUIItem.UserQuery(it.text)
                    }
                )
            }
        }
    }

    private fun checkText() {
        binding.apply {
            if (etQuery.text.isNullOrEmpty()) {
                Toast.makeText(this@ChatActivity, "Enter something", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertChatItem(etQuery.text.toString())
            }
        }
    }
}