package com.petruciostech.android_pedrohenriquemacdodasilva.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petruciostech.android_pedrohenriquemacdodasilva.data.CurrencyRepository
import com.petruciostech.android_pedrohenriquemacdodasilva.framework.retrofit.TaskApiCurrency
import com.petruciostech.android_pedrohenriquemacdodasilva.implementation.CurrencyListDataSourceImplementation
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyQuotesModel
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyQuotesResponseModel
import com.petruciostech.android_pedrohenriquemacdodasilva.usecase.CurrencyQuotesUseCase

class MainActivityViewModel:ViewModel() {
    companion object{
        const val TAG_ERROR = "##ERROR_MAIN##"
    }
    private val apiTask = TaskApiCurrency()
    private val dataSource = CurrencyListDataSourceImplementation(apiTask)
    private val repository = CurrencyRepository(dataSource)
    private val useCase = CurrencyQuotesUseCase(repository)

    private var _quotes = MutableLiveData<CurrencyQuotesModel>()
    val quotesModel:LiveData<CurrencyQuotesModel> get() = _quotes

    private var _origemValue = MutableLiveData<Double>()
    val origemValue:LiveData<Double> get() = _origemValue

    private var _destinyValue = MutableLiveData<Double>()
    val destinyValue:LiveData<Double> get() = _destinyValue

    fun init(){
        getAllQuotes()
    }

    private fun getAllQuotes(){
        Thread{
            try {
                _quotes.postValue(useCase.invoke())
            }catch (ex:Exception){
                Log.e(TAG_ERROR, ex.toString())
            }
        }.start()
    }

    fun convert(codeOrigem:String, codeDestiny:String,
                value:Double, listQuotes:CurrencyQuotesModel){
        //tranformando a list de pair em objeto
        val listaQuotes = listQuotes.quotes.toList()
        val listQuotesReturn = arrayListOf<CurrencyQuotesResponseModel>()
        for(i in listaQuotes.indices){
            listQuotesReturn
               .add(CurrencyQuotesResponseModel(listaQuotes[i].first, listaQuotes[i].second))
        }

        //achando o valor procurado
        val baseCode = "USD"
        val valueCodeOrigem = listQuotesReturn.find { it.code == baseCode+codeOrigem }
        val valueCodeDestiny = listQuotesReturn.find { it.code == baseCode+codeDestiny }

        //setando os valores dos textview
        if (valueCodeOrigem != null) {
            _origemValue.postValue(valueCodeOrigem.valueConvert * value)
        }

        if (valueCodeDestiny != null) {
            val origem = valueCodeOrigem?.valueConvert!!
            val destino = valueCodeDestiny?.valueConvert!!
            val result = (value * (1/origem))/destino
            _destinyValue.postValue(result)
        }

    }

}