package com.sachinrana.todogram.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinrana.todogram.data.Resource
import com.sachinrana.todogram.data.models.TodoEntity
import com.sachinrana.todogram.data.repository.TodoRepository
import com.sachinrana.todogram.utils.userNameMap
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val repository: TodoRepository) : ViewModel() {

    private val todoList = MutableLiveData<Resource<List<TodoEntity>>>()

    var todoListLiveData = MutableLiveData<Resource<List<TodoEntity>>>()
        get() = todoList

    private val handler = CoroutineExceptionHandler { _, throwable ->
        var msg = if (throwable.message != null) throwable.message as String else "Error"
        todoList.value = Resource.error(msg, null)
    }


    fun getTodoList() {

        todoList.value = Resource.loading(null)
        viewModelScope.launch(handler) {
            var list: List<TodoEntity> =
                repository.getTodoList().apply { forEach { it.userName = userNameMap[it.userId] } }
            list?.let {
                if (list.isEmpty()) todoList.value = Resource.empty()
                else todoList.value = Resource.success(it)
            }
        }
    }
}
