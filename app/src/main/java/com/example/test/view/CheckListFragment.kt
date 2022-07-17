package  com.example.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.test.data.utils.DataKeys.Companion.CHECKLIST_ID
import com.example.test.domain.CheckListModel
import com.example.test.presentation.CheckListViewModel
import com.example.test.presentation.ToDoViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CheckListFragment : Fragment() {

    private val checkListViewModel: CheckListViewModel by inject()
    private val toDoViewModel: ToDoViewModel by inject()
    private lateinit var binder: CheckListFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = CheckListFragmentBinder(this, ::onItemClick)
        return binder.bindView(inflater, container, savedInstanceState)
    }

    private fun onItemClick(view: View, checkListModel: CheckListModel) {
        toDoViewModel.loadToDoData(checkListModel.id)
        CHECKLIST_ID = checkListModel.id
        findNavController().navigate(
            CheckListFragmentDirections.actionCheckListFragmentToToDoFragment(
                checkListModel.title,
                checkListModel
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition(1000L, java.util.concurrent.TimeUnit.MILLISECONDS)

        checkListViewModel.loadDataCheckList()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                checkListViewModel.stateCheckList.collect {
                    if (it != null) {
                        if (it.isSuccess) {
                            binder.applyAdapter(it.getOrThrow())
                        }
                        if (it.isFailure) {
                            it.exceptionOrNull()
                        }
                    }
                }
            }
        }
    }
}