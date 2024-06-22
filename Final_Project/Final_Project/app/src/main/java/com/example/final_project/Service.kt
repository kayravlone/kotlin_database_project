package com.example.final_project

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("db")
    fun getData(): Call<List<Fruit>>

}