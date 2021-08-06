package com.petruciostech.android_pedrohenriquemacdodasilva.framework.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskApiCurrency {
    companion object{
        const val BASE_URL = "http://api.currencylayer.com/"
    }

    private fun currencyProvider(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retroFitApi():ApiMoeda = currencyProvider().create(ApiMoeda::class.java)
}