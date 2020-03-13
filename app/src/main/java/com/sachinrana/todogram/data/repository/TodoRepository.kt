package com.sachinrana.todogram.data.repository

import com.sachinrana.todogram.data.ApiService
import javax.inject.Inject

class TodoRepository
@Inject constructor(private val remote: ApiService) {

    suspend fun getTodoList() = remote.getTodoTaskList()

}