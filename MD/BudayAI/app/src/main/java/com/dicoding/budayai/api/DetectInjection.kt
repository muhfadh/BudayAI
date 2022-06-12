package com.dicoding.budayai.api

import android.content.Context
import com.dicoding.budayai.api.repository.DetectRepository
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object DetectInjection {
    fun injectDetect(context: Context): DetectRepository {
        val client = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(Interceptor())
            .build()
        val apiService = ApiDetect.getApiDetect(client)
        return DetectRepository.getInstance(apiService)
    }
}