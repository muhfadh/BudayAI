package com.dicoding.budayai.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.budayai.analys.AnalysModel
import com.dicoding.budayai.api.DetectInjection
import com.dicoding.budayai.api.repository.DetectRepository
import java.lang.IllegalArgumentException

class DetectModel private constructor(private val detectRepository: DetectRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AnalysModel::class.java) -> AnalysModel(detectRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found: ${modelClass.name}")
        }
    }
    companion object {
        private var instance: DetectModel? = null

        fun getInstance(context: Context): DetectModel = instance ?: synchronized(this){
            instance ?: DetectModel(
                DetectInjection.injectDetect(context)
            )
        }.also {
            instance = it
        }
    }
}