package com.dicoding.budayai.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.budayai.analys.AnalysModel
import com.dicoding.budayai.api.Injection
import com.dicoding.budayai.api.repository.DataRepository
import com.dicoding.budayai.home.HomeModel
import com.dicoding.budayai.location.LocationModel
import java.lang.IllegalArgumentException

class FactoryModel private constructor(private val dataRepository: DataRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeModel::class.java) -> HomeModel(dataRepository) as T
            modelClass.isAssignableFrom(LocationModel::class.java) -> LocationModel(dataRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found: ${modelClass.name}")
        }
    }
    companion object {
        private var instance: FactoryModel? = null

        fun getInstance(context: Context): FactoryModel = instance ?: synchronized(this){
            instance ?: FactoryModel(
                Injection.inject(context)
            )
        }.also {
            instance = it
        }
    }
}