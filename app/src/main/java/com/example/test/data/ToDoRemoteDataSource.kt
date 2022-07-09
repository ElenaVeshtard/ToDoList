package com.example.test.data

import com.example.test.domain.ToDoModel

interface ToDoRemoteDataSource {
    suspend fun getItemToDo():MutableList<ToDoModel>
}