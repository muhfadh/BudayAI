package com.dicoding.budayai.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.dicoding.budayai.api.ApiService
import com.dicoding.budayai.api.DataPreferences
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.location.StyleMap
import com.dicoding.budayai.location.TypeMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataRepository (private val dataPreferences: DataPreferences, private val apiService: ApiService){

    fun getData(): Call<List<ResponseClassItem>> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-budayai-c22-ps195.cloudfunctions.net/app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getClass()
    }

    fun getMap() : Call<List<ResponseClassItem>> = getData()
    fun getStyleMap(): LiveData<StyleMap> = dataPreferences.getStyleMap().asLiveData()
    fun getTypeMap(): LiveData<TypeMap> = dataPreferences.getTypeMap().asLiveData()

    suspend fun saveStyleMap(value: StyleMap) = dataPreferences.saveStyleMap(value)
    suspend fun saveTypeMap(value: TypeMap) = dataPreferences.saveTypeMap(value)
    companion object {
        @Volatile
        private var instance: DataRepository? = null

        @JvmStatic
        fun getInstance(dataPreferences: DataPreferences, apiService: ApiService) : DataRepository
                = instance ?: synchronized(this){
            instance ?: DataRepository(dataPreferences, apiService)
        }.also { instance = it }
    }
}