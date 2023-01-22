package com.example.localstorage.feat.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.localstorage.R
import com.example.localstorage.data.model.Employee
import com.example.localstorage.data.model.Todo
import com.example.localstorage.databinding.ActivityMainBinding
import com.example.localstorage.databinding.ActivityTodoBinding
import com.example.localstorage.util.Resource

class TodoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTodoBinding
    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var todoNumber = 100
        getAllTodos()

        binding.apply {
            btnAddTodo.setOnClickListener {
                viewModel.addTodo("Todo Title $todoNumber", "Todo Description $todoNumber")
                todoNumber++
            }

            btnShowAllTodo.setOnClickListener {
                getAllTodos()
            }
        }

    }

    private fun getAllTodos() {
        viewModel.getAllTodos().observe(this) {
            when (it) {
                is Resource.Default -> {
                    binding.cpiLoading.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.cpiLoading.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.cpiLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    setText(it.value)
                }
            }
        }
    }

    private fun setText(value: List<Todo>) {
        var data = ""
        value.forEach {
            data += "${it.id}\n" + it.title + "\n" + it.description + "\n\n\n"
        }
        binding.tvEmployees.text = data
    }
}