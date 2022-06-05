package com.dicoding.budayai.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiKatalog {
    fun getApiKatalog(client: OkHttpClient) : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-budayai-c22-ps195.cloudfunctions.net/app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}