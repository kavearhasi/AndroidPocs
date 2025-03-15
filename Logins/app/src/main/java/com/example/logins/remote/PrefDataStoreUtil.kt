package com.example.logins.remote

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject

class PrefDataStoreUtil @Inject constructor(private val dataPref:DataStore<Preferences>) {
     suspend fun  getStringValues(prefKey:Preferences.Key<String>): String? {
         var result:String ? = null
      dataPref.data.collect{
         result =  it[prefKey]
      }
         return result
    }
    suspend fun  getIntValues(prefKey:Preferences.Key<Int>): Int? {
        var result:Int ? = null

        dataPref.data.collect{
            result =  it[prefKey]
        }
        return result
    }
    suspend fun  getDoubleValues(prefKey:Preferences.Key<Double>): Double? {
        var result:Double ? = null

        dataPref.data.collect{
            result =  it[prefKey]
        }
        return result
    }
    suspend fun  getFloatValues(prefKey:Preferences.Key<Float>): Float? {
        var result:Float ? = null

        dataPref.data.collect{
            result =  it[prefKey]
        }
        return result
    }
    suspend fun  getBooleanValues(prefKey:Preferences.Key<Boolean>): Boolean? {
        var result:Boolean ? = null

        dataPref.data.collect{
            result =  it[prefKey]
        }
        return result
    }


    suspend fun<T:Any> setValues(prefKey:Preferences.Key<T>, value:T){
        try {

            dataPref.edit {
                it[prefKey] = value
            }
        }
        catch (e:Exception){
            e.message?.let { Log.e("prefDataStoreUtil:setValues()", it) }
        }
    }



}