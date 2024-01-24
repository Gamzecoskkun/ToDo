package com.gamzecoskun.todo.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gamzecoskun.todo.data.Repository
import com.gamzecoskun.todo.model.Priorty
import com.gamzecoskun.todo.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,private val repository: Repository
):AndroidViewModel(application) {

    val toDoList=repository.localDataSource.getAllToDo().asLiveData()

    fun insertToDo(){
        viewModelScope.launch {
            repository.localDataSource.insertToDo(ToDoModel(title="Deneme", description = "Deneme Açıklama", priorty = Priorty.HIGH, isChecked = true))
        }
    }
}