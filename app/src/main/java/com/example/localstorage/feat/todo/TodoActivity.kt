package com.example.localstorage.feat.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.localstorage.R
import com.example.localstorage.data.model.Todo
import com.example.localstorage.databinding.ActivityTodoBinding
import com.example.localstorage.feat.todo.adapter.TodoAdapter
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
                getAllTodos()
            }

            btnShowAllTodo.setOnClickListener {
                getAllTodos()
            }

            btnDeleteAllTodo.setOnClickListener {
                viewModel.deleteAllTodos()
                Toast.makeText(this@TodoActivity, "All todos cleared", Toast.LENGTH_SHORT).show()
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
                    setTodoList(it.value)
                }
            }
        }
    }

    private fun setTodoList(value: List<Todo>) {
        binding.apply {
            rvTodo.adapter = TodoAdapter(value.toMutableList()) {
                handleTouchListener(it)
            }
            rvTodo.adapter?.itemCount?.let { rvTodo.scrollToPosition(it - 1) }
        }
    }

    private fun handleTouchListener(it: TodoClickListenerAction) {
        when(it) {
            is TodoClickListenerAction.onClick -> {
                Toast.makeText(this, "Just Click @ ${it.position}", Toast.LENGTH_SHORT).show()
            }
            is TodoClickListenerAction.onLongPress -> {
                showTodoActionables(it.todo)
            }
        }
    }

    private fun showTodoActionables(todo: Todo) {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle(getString(R.string.choose_an_action))
        val pictureDialogItems =
            arrayOf(
                getString(R.string.edit_todo),
                getString(R.string.delete_todo)
            )
        pictureDialog.setItems(
            pictureDialogItems
        ) { _, which ->
            when (which) {
                0 -> {
                    redirectToNewActivityToEditThisTodoAndReturnResult(todo)
                }
                1 -> {
                    deleteThisTodo(todo)
                }
            }
        }
        pictureDialog.setCancelable(false)
        pictureDialog.show()
    }

    private fun redirectToNewActivityToEditThisTodoAndReturnResult(todo: Todo) {

    }

    private fun deleteThisTodo(todo: Todo) {
        viewModel.deleteTodo(todo)
        (binding.rvTodo.adapter as TodoAdapter).deleteTodo(todo)
    }

}