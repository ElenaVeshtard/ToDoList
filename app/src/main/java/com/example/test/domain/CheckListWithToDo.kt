package com.example.test.domain

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.Serializable


@Serializable
class CheckListWithToDo(
    @Embedded
    val checkList: CheckListModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkListId"
    )
    val toDos: List<ToDoModel>
)