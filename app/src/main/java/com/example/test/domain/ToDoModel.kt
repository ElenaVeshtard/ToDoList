package com.example.test.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "todo")
@Parcelize
@Serializable

data class ToDoModel(
    val title: String,
    var checkListId: Long
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var isChecked: Boolean = false
}