package com.gamzecoskun.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gamzecoskun.todo.model.ToDoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT* from to_do_table")
    fun getAllToDo(): Flow<List<ToDoModel>> //databasede yaptığım işlemler insert delete updated olsun

    @Query("SELECT*from to_do_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchToDo(searchQuery: String): Flow<List<ToDoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //aynı idli database nasıl tepki versin
    suspend fun insertToDo(ToDoModel: ToDoModel)

    @Update
    suspend fun updateToDo(toDoModel: ToDoModel)

    @Query("SELECT * FROM to_do_table WHERE id=:toDoId")
    suspend fun getToDo(toDoId: Int): ToDoModel

    @Delete
    suspend fun deleteToDo(toDoModel: ToDoModel)
}