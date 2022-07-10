package com.example.test.data.room.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test.domain.CheckListModel
import com.example.test.domain.CheckListWithToDo

@Dao
interface CheckListDao {

    @Insert
    suspend fun saveCheckLists(checkLists: List<CheckListModel>)

    @Query("SELECT * FROM check_list")
    suspend fun findCheckList(): List<CheckListModel>

    @Query("select * from  check_list where id = :checkListId")
    fun findCheckListById(checkListId: Long): CheckListModel

    @Query("select * from  check_list where id = :checkListId")
    suspend fun findCheckListWithToDo(checkListId: Long): CheckListWithToDo
}
