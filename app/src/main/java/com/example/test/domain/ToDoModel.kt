package com.example.test.domain

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class ToDoModel(
    val title: String,
    var isChecked: Boolean = false
) : Parcelable {
    fun transitionId() = "" + title

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readBoolean()
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeBoolean(isChecked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ToDoModel> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): ToDoModel {
            return ToDoModel(parcel)
        }

        override fun newArray(size: Int): Array<ToDoModel?> {
            return arrayOfNulls(size)
        }
    }
}