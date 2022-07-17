package com.example.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.room.db.Repository
import com.example.test.domain.CheckListWithToDo
import com.example.test.domain.ToDoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: Repository) : ViewModel() {

    private val _stateToDo = MutableStateFlow<Result<CheckListWithToDo>?>(null)
    val stateToDo: StateFlow<Result<CheckListWithToDo>?> = _stateToDo

    fun loadToDoData(checkListId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                val toDos: CheckListWithToDo = repository.getToDos(checkListId)
                Result.success(toDos)

            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateToDo.emit(result)
        }
    }

    suspend fun saveToDoData(toDo: ToDoModel) {
        repository.saveToDo(toDo)
    }

    suspend fun deleteToDosByIds(toDos: List<Long>) {
        repository.deleteToDosByIds(toDos)
    }
}