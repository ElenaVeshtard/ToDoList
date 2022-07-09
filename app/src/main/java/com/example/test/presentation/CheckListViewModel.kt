package  com.example.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.CheckListRemoteDataSource
import com.example.test.data.CheckListRemoteDataSourceFake
import com.example.test.domain.CheckListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CheckListViewModel : ViewModel() {

    private val checkListRemoteDataSource: CheckListRemoteDataSource = CheckListRemoteDataSourceFake()

    private val _stateCheckList = MutableStateFlow<Result<List<CheckListModel>>?>(null)
    val stateCheckList: StateFlow<Result<List<CheckListModel>>?> = _stateCheckList

    fun loadDataCheckList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(checkListRemoteDataSource.getItemCheckList())
            } catch (t: Throwable) {
                Result.failure(t)
            }
            _stateCheckList.emit(result)
        }

    }
}