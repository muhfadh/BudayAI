package com.dicoding.budayai.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dicoding.budayai.location.StyleMap
import com.dicoding.budayai.location.TypeMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveStyleMap(styleMap: StyleMap){
        dataStore.edit {
            it[STYLE_MAP] = when(styleMap){
                StyleMap.NIGHT -> StyleMap.NIGHT.name
                StyleMap.NORMAL -> StyleMap.NORMAL.name
            }
        }
    }

    suspend fun saveTypeMap(typeMap: TypeMap){
        dataStore.edit {
            it[TYPE_MAP] = when(typeMap){
                TypeMap.SATELLITE -> TypeMap.SATELLITE.name
                TypeMap.NORMAL -> TypeMap.NORMAL.name
            }
        }
    }

    fun getTypeMap(): Flow<TypeMap> = dataStore.data.map {
        when(it[TYPE_MAP]){
            TypeMap.SATELLITE.name -> TypeMap.SATELLITE
            TypeMap.NORMAL.name -> TypeMap.NORMAL
            else -> TypeMap.NORMAL
        }
    }

    fun getStyleMap(): Flow<StyleMap> = dataStore.data.map {
        when (it[STYLE_MAP]){
            StyleMap.NIGHT.name -> StyleMap.NIGHT
            StyleMap.NORMAL.name -> StyleMap.NORMAL
            else -> StyleMap.NORMAL
        }
    }

    companion object{
        private val STYLE_MAP = stringPreferencesKey("STYLE_MAP")
        private val TYPE_MAP = stringPreferencesKey("TYPE_MAP")

        @Volatile
        private var INSTANCE: DataPreferences? = null
        fun getInstance(dataStore: DataStore<Preferences>) : DataPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = DataPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}