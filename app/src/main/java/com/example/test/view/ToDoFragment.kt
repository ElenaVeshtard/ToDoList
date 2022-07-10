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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.data.utils.DataKeys.Companion.CHECKLIST_ID
import com.example.test.domain.ToDoModel
import com.example.test.presentation.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class ToDoFragment : Fragment() {

    private val toDoViewModel: ToDoViewModel by inject()
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binder: ToDoFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binder = ToDoFragmentBinder(this) { _, post ->
            findNavController().navigate(
                ToDoFragmentDirections.actionToDoFragmentToCheckListFragment(
                    post,
                    post.title
                )
            )
        }
        return binder.bindView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)
        toDoViewModel.loadToDoData(CHECKLIST_ID)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                toDoViewModel.stateToDo.collect {
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

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                toDoViewModel.stateToDo.collect {
                    var toDos = it?.getOrThrow()?.toDos
                    if (toDos.isNullOrEmpty()) {
                        toDos = mutableListOf()
                    }
                    todoAdapter = TodoAdapter(toDos as MutableList<ToDoModel>)
                    rvTodoItems.adapter = todoAdapter
                    rvTodoItems.layoutManager = LinearLayoutManager(context)
                }
            }
        }

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = ToDoModel(todoTitle, CHECKLIST_ID)
                todoAdapter.addTodo(todo)
                lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        toDoViewModel.saveToDoData(todo)
                    }
                }
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodos.setOnClickListener {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    toDoViewModel.deleteToDosByIds(todoAdapter.deleteDoneTodos())
                }
            }
        }
    }
}
