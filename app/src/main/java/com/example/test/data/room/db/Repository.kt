package com.example.test.data.room.db

import androidx.room.Transaction
import com.example.test.data.CheckListRemoteDataSourceFake
import com.example.test.domain.CheckListModel
import com.example.test.domain.CheckListWithToDo
import com.example.test.domain.ToDoModel

class Repository(
    private val dbDataSource: DbDataSource,
    private val dbDataSourceFake: CheckListRemoteDataSourceFake
) {

    @Transaction
    suspend fun getCheckList(): List<CheckListModel> {
        var cache = dbDataSource.getCheckLists()
        if (cache.isEmpty()) {
            cache = dbDataSourceFake.getItemCheckList()
            dbDataSource.saveCheckLists(cache)
        }
        return cache
    }

    @Transaction
    suspend fun getToDos(checkListId: Long): CheckListWithToDo {
        return dbDataSource.getCheckListById(checkListId)
    }

    @Transaction
    suspend fun saveToDo(toDo: ToDoModel) {
        dbDataSource.saveToDo(toDo)
    }

    @Transaction
    suspend fun deleteToDosByIds(toDos: List<Long>) {
        dbDataSource.deleteToDosByIds(toDos)
    }
}