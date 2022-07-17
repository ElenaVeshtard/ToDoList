package com.example.test.data.room.db

import com.example.test.data.room.model.MyDatabase
import com.example.test.domain.CheckListModel
import com.example.test.domain.CheckListWithToDo
import com.example.test.domain.ToDoModel

class RoomDataSource(database: MyDatabase) : DbDataSource {

    private val checkListDao = database.provideCheckListDao()
    private val toDoDao = database.provideToDoDao()

    override suspend fun saveCheckLists(checkLists: List<CheckListModel>) {
        checkListDao.saveCheckLists(checkLists)
    }

    override suspend fun getCheckLists(): List<CheckListModel> {
        return checkListDao.findCheckList()
    }

    override suspend fun getCheckListById(checkListId: Long): CheckListWithToDo {
        return checkListDao.findCheckListWithToDo(checkListId)
    }

    override suspend fun saveToDo(toDo: ToDoModel) {
       return toDoDao.saveToDo(toDo)
    }

    override suspend fun deleteToDo(toDo: ToDoModel) {
        return toDoDao.deleteToDo(toDo)
    }

    override suspend fun deleteToDosByIds(toDos: List<Long>) {
        return toDoDao.deleteToDosByIds(toDos)
    }
}