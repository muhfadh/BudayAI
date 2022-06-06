package com.dicoding.budayai.api

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.dicoding.budayai.api.repository.DataRepository
import com.dicoding.budayai.dataStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object Injection {
    fun inject(context: Context): DataRepository {
        val userPreferences = DataPreferences.getInstance(context.dataStore)
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val apiService = ApiKatalog.getApiKatalog(client)
        return DataRepository.getInstance(userPreferences, apiService)
    }
}