package com.example.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.CheckListRemoteDataSource
import com.example.test.data.CheckListRemoteDataSourceFake
import com.example.test.data.ToDoRemoteDataSource
import com.example.test.data.ToDoRemoteDataSourceFake
import com.example.test.domain.CheckListModel
import com.example.test.domain.ToDoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel : ViewModel() {

    private val toDoRemoteDataSource: ToDoRemoteDataSource = ToDoRemoteDataSourceFake()

    private val _stateToDo = MutableStateFlow<Result<MutableList<ToDoModel>>?>(null)
    val stateToDo: StateFlow<Result<MutableList<ToDoModel>>?> = _stateToDo

    fun loadToDoData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(toDoRemoteDataSource.getItemToDo())
            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateToDo.emit(result)
        }

    }
}