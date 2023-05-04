package com.example.localstorage.features.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.localstorage.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }
    private val adapter by lazy {
        ChatAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            checkText()
        }

    }

    private fun checkText() {
        binding.apply {
            if(etQuery.text.isNullOrEmpty()) {
                Toast.makeText(this@ChatActivity, "Enter something", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertChatItem(etQuery.text.toString())
            }
        }
    }
}