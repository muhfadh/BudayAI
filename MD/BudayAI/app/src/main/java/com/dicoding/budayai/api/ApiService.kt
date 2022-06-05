package com.dicoding.budayai.api

import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.api.response.ResponseHomeItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/home")
    fun getHome() : Call<List<ResponseHomeItem>>

    @GET("api/v1/class")
    fun getClass(
        @Query("location") location: Int = 0
    ) : Call<List<ResponseClassItem>>
}