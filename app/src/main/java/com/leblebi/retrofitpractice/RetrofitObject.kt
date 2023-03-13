package com.leblebi.retrofitpractice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    val api : TodoAPI by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(TodoAPI::class.java)
    }
}