package com.dicoding.budayai.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.budayai.api.repository.DataRepository
import com.dicoding.budayai.api.response.ResponseClassItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModel(private val dataRepository: DataRepository): ViewModel() {

    fun getData(){
        val client = dataRepository.getData()
        client.enqueue(object : Callback<List<ResponseClassItem>> {
            //            override fun onResponse(
//                call: Call<ResponseClass>,
//                response: retrofit2.Response<ResponseClass>
//            ) {
//                if (response.isSuccessful){
//                    val responses = response.body()?.responseClass
//                    _data.postValue(responses!! as ArrayList<ResponseClassItem>?)
//                }            }
//
//            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
//                //
//            }
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

    private var _data = MutableLiveData<List<ResponseClassItem>>()
    val data: LiveData<List<ResponseClassItem>> = _data
}