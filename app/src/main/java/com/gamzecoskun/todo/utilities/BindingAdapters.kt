package com.gamzecoskun.todo.utilities

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gamzecoskun.todo.R
import com.gamzecoskun.todo.model.Priorty
import com.gamzecoskun.todo.model.ToDoModel
import com.gamzecoskun.todo.ui.home.HomeListAdapter
import com.gamzecoskun.todo.ui.home.ToDoClickListener

@BindingAdapter("setItemToDoPriorityTint")
fun setItemToDoPriorityTint(imageView: ImageView,priorty: Priorty?){
    val context=imageView.context

    val color=when(priorty){
        Priorty.HIGH-> R.color.priority_high
        Priorty.MEDIUM->R.color.md_theme_light_secondary
        else->R.color.seed
    }

    ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context,color)))

}

@BindingAdapter("toDoList","setOnClickListener")
fun setHomeRecyclerViewAdapter(
    recyclerView: RecyclerView,
    list:List<ToDoModel>?,
    toDoClickListener: ToDoClickListener
) {
    recyclerView.apply{
        if(this.adapter==null){
            adapter=HomeListAdapter(toDoClickListener).apply {submitList(list)}
        }else{
            (this.adapter as HomeListAdapter).submitList(list)
        }
    }
}