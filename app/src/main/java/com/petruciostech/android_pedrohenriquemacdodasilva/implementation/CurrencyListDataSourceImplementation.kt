package com.petruciostech.android_pedrohenriquemacdodasilva.implementation

import android.util.Log
import com.petruciostech.android_pedrohenriquemacdodasilva.data.CurrencyListDataSource
import com.petruciostech.android_pedrohenriquemacdodasilva.framework.retrofit.TaskApiCurrency
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyModel

class CurrencyListDataSourceImplementation(private val apiTask:TaskApiCurrency)
    :CurrencyListDataSource{
    companion object{
        const val TAG_ERROR = "##error##"
    }
    private var listCurrency:CurrencyModel? = null
    override fun getAllCurrency():CurrencyModel? {
        val request = apiTask.retroFitApi().getListCurrency().execute()

        if(request.isSuccessful){
            request.body().let { listCurrency = it }
            Log.i("##Informar##", request.body().toString())
        }else{
            Log.e(TAG_ERROR, request.errorBody().toString())
        }

        return listCurrency
    }
}