package com.leblebi.retrofitpractice

import retrofit2.http.GET


interface TodoAPI {
    @GET ("/todos")
    suspend fun getTodos():retrofit2.Response<List<Todo>>
}