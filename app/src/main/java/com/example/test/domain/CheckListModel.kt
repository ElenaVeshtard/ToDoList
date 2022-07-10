package com.example.test.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "check_list")
@Serializable
@Parcelize
data class CheckListModel(

    @PrimaryKey
    val id: Long,
    val image: String,
    val title: String,
    val subtitle: String,
    val button: String
) : Parcelable {
    fun transitionId() = "" + title + subtitle
}