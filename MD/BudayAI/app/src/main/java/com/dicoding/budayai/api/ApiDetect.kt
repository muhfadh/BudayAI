package com.dicoding.budayai.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDetect {
    fun getApiDetect(client: OkHttpClient) : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://budayai-7zcxfbjfqq-uc.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}