package  com.example.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.CheckListFragmentBinding
import com.example.test.domain.CheckListModel

class CheckListFragmentBinder(
    private val fragment: Fragment,
    private val onItemClick: (View, CheckListModel) -> Unit
) {
    private lateinit var binding: CheckListFragmentBinding
    fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CheckListFragmentBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(fragment.requireActivity())

        binding.recyclerView.layoutManager = layoutManager
        return binding.root
    }

    fun applyAdapter(list: List<CheckListModel>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.recyclerView.adapter = CheckListAdapter(list, onItemClick)
    }
}