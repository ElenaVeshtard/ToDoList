package com.example.test.data.room.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.domain.CheckListModel
import com.example.test.domain.ToDoModel

@Database(entities = [CheckListModel::class, ToDoModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun provideCheckListDao(): CheckListDao
    abstract fun provideToDoDao(): ToDoDao
}

