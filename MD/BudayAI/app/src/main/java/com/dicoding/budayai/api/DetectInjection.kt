package com.dicoding.budayai.api

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.dicoding.budayai.api.repository.DataRepository
import com.dicoding.budayai.api.repository.DetectRepository
import com.dicoding.budayai.dataStore
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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