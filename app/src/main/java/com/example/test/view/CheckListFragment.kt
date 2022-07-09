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
import com.example.test.presentation.CheckListViewModel
import kotlinx.coroutines.launch

class CheckListFragment : Fragment() {

    private val viewModel: CheckListViewModel by viewModels()
    private lateinit var binder: CheckListFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binder = CheckListFragmentBinder(this) { view, post ->
            findNavController().navigate(
                CheckListFragmentDirections.actionPostsFragmentToFullscreenFragment(
                    post.title,
                    post
                )
            )
        }
        return binder.bindView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition(1000L, java.util.concurrent.TimeUnit.MILLISECONDS)
        viewModel.loadDataCheckList()

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.stateCheckList.collect {
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
    }
}