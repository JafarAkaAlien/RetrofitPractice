package com.leblebi.retrofitpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leblebi.retrofitpractice.databinding.ItemTodoBinding

class TodoAdaptor:RecyclerView.Adapter<TodoAdaptor.TodoViewHolder>() {

    inner class TodoViewHolder(val binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object :DiffUtil.ItemCallback<Todo>(){
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    var todos: List<Todo>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun getItemCount()= todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            Texttitle.text = todo.title
            checkbox.isChecked = todo.completed
        }
    }
}