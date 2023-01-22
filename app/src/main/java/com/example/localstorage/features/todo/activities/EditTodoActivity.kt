package com.example.localstorage.features.todo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.example.localstorage.R
import com.example.localstorage.data.model.Todo
import com.example.localstorage.databinding.ActivityEditTodoBinding
import com.example.localstorage.extension.showKeyboard
import com.example.localstorage.features.todo.viewmodel.EditTodoViewModel
import com.example.localstorage.util.BundleDataIdentifier

class EditTodoActivity : AppCompatActivity() {

    private val viewModel by viewModels<EditTodoViewModel>()
    private lateinit var binding: ActivityEditTodoBinding
    private lateinit var todo : Todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getArgs()
        setClickListener()
    }

    private fun getArgs() {
        todo = intent.extras?.getParcelable<Todo>(BundleDataIdentifier.TODO) as Todo
        binding.apply {
            etTitle.setText(todo.title)
            etDescription.setText(todo.description)
            etTitle.setSelection(todo.title.length)

            showKeyboard()
        }
    }

    private fun showKeyboard() {
        binding.root.showKeyboard()
    }

    private fun setClickListener() {
        binding.apply {
            btnSaveTodoDetail.setOnClickListener {
                if (TextUtils.isEmpty(etTitle.text)) {
                    Toast.makeText(this@EditTodoActivity,
                        getString(R.string.error_empty_title),
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (TextUtils.isEmpty(etDescription.text)) {
                    Toast.makeText(this@EditTodoActivity,
                        getString(R.string.error_empty_description),
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                todo.title = etTitle.text.toString()
                todo.description = etDescription.text.toString()
                saveUpdatedTodo(todo)
            }
        }
    }

    private fun saveUpdatedTodo(todo: Todo) {
        viewModel.updateTodo(todo)
        val intent = Intent()
        intent.putExtra(BundleDataIdentifier.TODO, todo)
        setResult(RESULT_OK, intent)
        finish()
    }


}