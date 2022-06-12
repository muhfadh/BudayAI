package com.dicoding.budayai.api

import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.api.response.ResponseDetect
import com.dicoding.budayai.api.response.ResponseHomeItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/v1/home")
    fun getHome() : Call<List<ResponseHomeItem>>

    @GET("api/v1/class")
    fun getClass(
        @Query("location") location: Int = 0
    ) : Call<List<ResponseClassItem>>

    @Multipart
    @POST("detect")
    fun postDetect(
        @Part file : MultipartBody.Part,
        @Part("select_model") select_model: RequestBody
    ) : Call<ResponseDetect>
}