package  com.example.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.domain.ToDoModel
import com.example.test.presentation.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ToDoFragment : Fragment() {
    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binder: ToDoFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binder = ToDoFragmentBinder(this) { view, post ->
            findNavController().navigate(
                ToDoFragmentDirections.actionFullscreenFragmentToPostsFragment(
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
        viewModel.loadToDoData()

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.stateToDo.collect {
                    if (it != null) {
                        if (it.isSuccess) {

                            binder.applyAdapter(it.getOrThrow())
                        }
                        if (it.isFailure) {
                            binder.showProgress()
                        }
                    }
                }
            }
        }

        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(context)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = ToDoModel(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}
