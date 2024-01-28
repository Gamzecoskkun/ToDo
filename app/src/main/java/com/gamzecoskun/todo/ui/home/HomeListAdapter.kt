package com.gamzecoskun.todo.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gamzecoskun.todo.databinding.ItemTodoBinding
import com.gamzecoskun.todo.model.ToDoModel

class HomeListAdapter(private val toDoClickListener: ToDoClickListener): ListAdapter<ToDoModel, HomeListAdapter.ViewHolder>(ToDoDiffCallBack()) {
    class ViewHolder(private val binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoClickListener: ToDoClickListener,toDoModel: ToDoModel){
            binding.toDoModel=toDoModel
            binding.toDoClickListener=toDoClickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val binding =ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

                return ViewHolder(binding)
            }
        }
    }

    private class ToDoDiffCallBack:DiffUtil.ItemCallback<ToDoModel>(){
        override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder=ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(toDoClickListener,currentList[position])
    }
}

interface ToDoClickListener{
    fun onToDoClick(id:Int)
    fun onToDoChecked(toDoModel: ToDoModel)
}