package com.sachinrana.todogram.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.sachinrana.todogram.data.repository.TodoRepository
import com.sachinrana.todogram.ui.main.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(todoRepository)
                else ->
                    error("Invalid View Model class")
            }

        } as T
    }
}