package com.example.test.data

import com.example.test.domain.ToDoModel

class ToDoRemoteDataSourceFake : ToDoRemoteDataSource {
    override suspend fun getItemToDo(): MutableList<ToDoModel> {
        return arrayListOf(
            ToDoModel(
                "Полотенце",
                false
            )
        )
    }

}