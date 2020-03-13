package com.sachinrana.todogram.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sachinrana.todogram.R
import com.sachinrana.todogram.TodoApplication
import com.sachinrana.todogram.data.Resource
import com.sachinrana.todogram.data.Status
import com.sachinrana.todogram.data.models.TodoEntity
import com.sachinrana.todogram.factory.ViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TodoListAdapter
    private lateinit var mainLayout: SwipeRefreshLayout

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity().application as TodoApplication).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        adapter = TodoListAdapter(requireActivity())
        rvTodoList.adapter = adapter
        viewModel.getTodoList()
        viewModel.todoListLiveData.observe(this, observer)
        mainLayout = main
        mainLayout.setOnRefreshListener {
            viewModel.getTodoList()
        }
    }

    private var observer = Observer<Resource<List<TodoEntity>>> {
        when (it.status) {
            Status.LOADING -> {
                rvTodoList.showShimmer()
            }
            Status.SUCCESS -> {
                rvTodoList.hideShimmer()
                layoutError.visibility = View.GONE
                layoutResult.visibility = View.VISIBLE
                mainLayout.isRefreshing = false
                adapter.todoList = it.data!!
            }
            Status.EMPTY, Status.ERROR -> {
                rvTodoList.hideShimmer()
                layoutError.visibility = View.VISIBLE
                layoutResult.visibility = View.GONE
                mainLayout.isRefreshing = false
            }
        }
    }

}
