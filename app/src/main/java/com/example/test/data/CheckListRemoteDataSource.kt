package com.example.test.data

import com.example.test.domain.CheckListModel

interface CheckListRemoteDataSource {
    suspend fun getItemCheckList(): List<CheckListModel>
}
