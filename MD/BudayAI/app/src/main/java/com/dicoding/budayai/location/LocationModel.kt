package com.dicoding.budayai.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.budayai.api.repository.DataRepository
import com.dicoding.budayai.api.response.ResponseClassItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class LocationModel(private val dataRepository: DataRepository): ViewModel() {
    private var _data = MutableLiveData<ArrayList<ResponseClassItem>>()
    val data: LiveData<ArrayList<ResponseClassItem>> = _data
    var locationPermission = MutableLiveData<Boolean>()

    fun getStyleMap(): LiveData<StyleMap> = dataRepository.getStyleMap()
    fun getTypeMap(): LiveData<TypeMap> = dataRepository.getTypeMap()

    fun setLocationPermission(p: Boolean){
        locationPermission.value = p
    }

    fun getDataLocation(){
        val client = dataRepository.getMap()
        client.enqueue(object : retrofit2.Callback<List<ResponseClassItem>>{

            //            override fun onResponse(
//                call: Call<ResponseClass>,
//                response: retrofit2.Response<ResponseClass>
//            ) {
//                if (response.isSuccessful){
//                    val responses = response.body()?.responseClass
//                    _data.postValue(responses!! as ArrayList<ResponseClassItem>?)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
//                //no action
//            }
            override fun onResponse(
                call: Call<List<ResponseClassItem>>,
                response: Response<List<ResponseClassItem>>
            ) {
                if (response.isSuccessful){
                    val responses = response.body()
                    _data.postValue(responses!! as ArrayList<ResponseClassItem>?)
                }
            }

            override fun onFailure(call: Call<List<ResponseClassItem>>, t: Throwable) {
                //no action
            }

        })
    }

    fun saveStyleMap(styleMap: StyleMap){
        viewModelScope.launch {
            dataRepository.saveStyleMap(styleMap)
        }
    }

    fun saveTypeMap(typeMap: TypeMap){
        viewModelScope.launch {
            dataRepository.saveTypeMap(typeMap)
        }
    }

}