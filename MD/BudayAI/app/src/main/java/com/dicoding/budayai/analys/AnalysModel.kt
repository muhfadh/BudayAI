package com.dicoding.budayai.analys

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.budayai.api.repository.DetectRepository
import com.dicoding.budayai.api.response.ResponseDetect
import com.dicoding.budayai.util.Event
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class AnalysModel(private val detectRepository: DetectRepository): ViewModel() {

    private var _message = MutableLiveData<Event<String>>()
    private var _error = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> = _error
    private var _analys = MutableLiveData<ResponseDetect>()
    val analys: LiveData<ResponseDetect> = _analys

    fun addDetect(file: MultipartBody.Part, select_name: RequestBody){
        val client = detectRepository.addDetect(file, select_name)
        client.enqueue(object : retrofit2.Callback<ResponseDetect>{
            override fun onResponse(call: Call<ResponseDetect>, response: Response<ResponseDetect>) {
                if (response.isSuccessful){
                    _analys.postValue(response.body())
                } else {
                    _message.postValue(Event(response.message()))
                    _error.postValue(Event(true))
                }
            }

            override fun onFailure(call: Call<ResponseDetect>, t: Throwable) {
                _message.value = Event(t.message.toString())
                _error.value = Event(true)
            }
        })
    }
}