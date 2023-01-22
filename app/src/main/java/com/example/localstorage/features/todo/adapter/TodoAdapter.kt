package com.example.localstorage.features.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.localstorage.data.model.Todo
import com.example.localstorage.databinding.ItemTodoBinding
import com.example.localstorage.features.todo.TodoClickListenerAction

class TodoAdapter(
    private val todoList: MutableList<Todo>,
    private val action: (TodoClickListenerAction) -> Unit,
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTodoTitle.text = todoList[position].title
            tvTodoDescription.text = todoList[position].description

            root.setOnClickListener {
                action.invoke(TodoClickListenerAction.onClick(todoList[position], position))
            }

            root.setOnLongClickListener {
                action.invoke(TodoClickListenerAction.onLongPress(todoList[position], position))
                true
            }
        }
    }

    override fun getItemCount() = todoList.size

    fun deleteTodo(todo: Todo) {
        if (todoList.contains(todo)) {
            todoList.remove(todo)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

}