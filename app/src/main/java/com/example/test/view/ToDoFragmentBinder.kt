package com.example.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ToDoFragmentBinding
import com.example.test.domain.ToDoModel

class ToDoFragmentBinder(
    private val fragment: Fragment,
    private val onItemClick: (View, ToDoModel) -> Unit
) {
    private lateinit var binding: ToDoFragmentBinding
    fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ToDoFragmentBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(fragment.requireActivity())

        binding.rvTodoItems.layoutManager = layoutManager
        return binding.root
    }

    fun applyAdapter(list: MutableList<ToDoModel>) {
        binding.rvTodoItems.visibility = View.VISIBLE
    }

    fun showProgress() {

        binding.rvTodoItems.visibility = View.GONE

    }
}