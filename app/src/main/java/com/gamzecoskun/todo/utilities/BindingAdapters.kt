package com.gamzecoskun.todo.utilities

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gamzecoskun.todo.R
import com.gamzecoskun.todo.model.Priority
import com.gamzecoskun.todo.model.ToDoModel
import com.gamzecoskun.todo.ui.home.HomeListAdapter
import com.gamzecoskun.todo.ui.home.ToDoClickListener

@BindingAdapter("setItemToDoPriorityTint")
fun setItemToDoPriorityTint(imageView: ImageView, priority: Priority?) {
    val context = imageView.context

    val color = when (priority) {
        Priority.HIGH -> R.color.priority_high
        Priority.MEDIUM -> R.color.md_theme_light_secondary
        else -> R.color.seed
    }

    ImageViewCompat.setImageTintList(
        imageView,
        ColorStateList.valueOf(ContextCompat.getColor(context, color))
    )

}

@BindingAdapter("toDoList", "setOnClickListener", "searchQuery", "searchToDoList")
fun setHomeRecyclerViewAdapter(
    recyclerView: RecyclerView,
    list: List<ToDoModel>?,
    toDoClickListener: ToDoClickListener,
    searchQuery: String,
    searchList: List<ToDoModel>?
) {
    recyclerView.apply {
        if (this.adapter == null) {
            adapter = HomeListAdapter(toDoClickListener).apply {
                submitList(
                    if (searchQuery.isEmpty()) {
                        list
                    } else {
                        searchList
                    }
                )
            }
        } else {
            (this.adapter as HomeListAdapter).submitList(
                if (searchQuery.isEmpty()) {
                    list
                } else {
                    searchList
                }
            )
        }
    }
}