package com.sachinrana.todogram.data

import com.sachinrana.todogram.data.models.TodoEntity
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodoTaskList(): List<TodoEntity>
}