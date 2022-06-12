package com.dicoding.budayai.api.repository

import com.dicoding.budayai.api.ApiService
import com.dicoding.budayai.api.response.ResponseDetect
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DetectRepository (private val apiService: ApiService) {

    private fun getDetect(): ApiService{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://budayai-7zcxfbjfqq-uc.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun addDetect(file: MultipartBody.Part, select_model: RequestBody): Call<ResponseDetect> = getDetect().postDetect(file, select_model)

    companion object{
        @Volatile
        private var instance: DetectRepository? = null

        @JvmStatic
        fun getInstance(apiService: ApiService) : DetectRepository
                = instance ?: synchronized(this){
            instance ?: DetectRepository(apiService)
        }.also { instance = it }
    }
}