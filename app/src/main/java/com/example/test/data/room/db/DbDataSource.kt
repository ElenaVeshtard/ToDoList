package com.example.test.data.room.db

import com.example.test.domain.CheckListModel
import com.example.test.domain.CheckListWithToDo
import com.example.test.domain.ToDoModel

interface DbDataSource {

    suspend fun saveCheckLists(checkLists: List<CheckListModel>)
    suspend fun getCheckLists(): List<CheckListModel>
    suspend fun getCheckListById(checkListId: Long): CheckListWithToDo

    suspend fun saveToDo(toDo: ToDoModel)
    suspend fun deleteToDo(toDo: ToDoModel)
    suspend fun deleteToDosByIds(toDos: List<Long>)
}

