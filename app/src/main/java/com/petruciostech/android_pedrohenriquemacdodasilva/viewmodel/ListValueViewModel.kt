package com.petruciostech.android_pedrohenriquemacdodasilva.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petruciostech.android_pedrohenriquemacdodasilva.data.CurrencyRepository
import com.petruciostech.android_pedrohenriquemacdodasilva.framework.retrofit.TaskApiCurrency
import com.petruciostech.android_pedrohenriquemacdodasilva.implementation.CurrencyListDataSourceImplementation
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyModel
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyResponseModel
import com.petruciostech.android_pedrohenriquemacdodasilva.usecase.CurrencyListUseCase

class ListValueViewModel:ViewModel() {
    companion object{
        const val TAG_ERROR = "##error##"
    }
    private val apiTask = TaskApiCurrency()
    private val dataSource = CurrencyListDataSourceImplementation(apiTask)
    private val repository = CurrencyRepository(dataSource)
    private val useCase = CurrencyListUseCase(repository)

    private var _currencyList = MutableLiveData<CurrencyModel>()
    val currencyList:LiveData<CurrencyModel> get() = _currencyList

    fun init(){
        getAllCurrency()
    }

    private fun getAllCurrency(){
        Thread{
            try {
                _currencyList.postValue(useCase.invoke())
            }catch (ex:Exception){
                Log.e(TAG_ERROR, "Houve algum erro: ${ex.message}")
            }
        }.start()
    }

    fun formarList(map:List<Pair<String, String>>):List<CurrencyResponseModel>{
        val list = arrayListOf<CurrencyResponseModel>()
        for(i in map.indices){
            list.add(CurrencyResponseModel(map[i].first, map[i].second))
        }
        return list
    }

}