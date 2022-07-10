package com.example.test.data.room.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.test.domain.ToDoModel

@Dao
interface ToDoDao {

    @Insert
    suspend fun saveToDo(toDo: ToDoModel)

    @Delete
    suspend fun deleteToDo(toDo: ToDoModel)

    @Query("delete from todo where id in (:toDoIds)")
    suspend fun deleteToDosByIds(toDoIds: List<Long>)
}
