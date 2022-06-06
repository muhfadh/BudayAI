package com.dicoding.budayai.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.budayai.api.repository.DataRepository
import com.dicoding.budayai.api.response.ResponseClassItem
import com.dicoding.budayai.api.response.ResponseHomeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModel(private val dataRepository: DataRepository): ViewModel() {

    fun getDataHome(){
        val client = dataRepository.getDataHome()
        client.enqueue(object : Callback<List<ResponseHomeItem>> {
            override fun onResponse(
                call: Call<List<ResponseHomeItem>>,
                response: Response<List<ResponseHomeItem>>
            ) {
                if (response.isSuccessful){
                    val responses = response.body()
                    _dataHome.postValue(responses!!)
                }
            }

            override fun onFailure(call: Call<List<ResponseHomeItem>>, t: Throwable) {
                _message.value = t.message
            }
        })
    }

    fun getData(){
        val client = dataRepository.getData()
        client.enqueue(object : Callback<List<ResponseClassItem>> {
            override fun onResponse(
                call: Call<List<ResponseClassItem>>,
                response: Response<List<ResponseClassItem>>
            ) {
                if (response.isSuccessful){
                    val responses = response.body()
                    _data.postValue(responses!!)
                }
            }

            override fun onFailure(call: Call<List<ResponseClassItem>>, t: Throwable) {
                _message.value = t.message
            }
        })
    }

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var _dataHome = MutableLiveData<List<ResponseHomeItem>>()
    val dataHome: LiveData<List<ResponseHomeItem>> = _dataHome

    private var _data = MutableLiveData<List<ResponseClassItem>>()
    val data: LiveData<List<ResponseClassItem>> = _data
}