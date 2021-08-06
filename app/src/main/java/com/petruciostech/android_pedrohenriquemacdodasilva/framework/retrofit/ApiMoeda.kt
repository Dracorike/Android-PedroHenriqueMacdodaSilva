package com.petruciostech.android_pedrohenriquemacdodasilva.framework.retrofit

import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyModel
import retrofit2.Call
import retrofit2.http.GET
interface ApiMoeda {
    //http://api.currencylayer.com/live?access_key=5ed10cd86dd9f0cb7cf8b7d0fb75e8b1
    @GET("list?access_key=5ed10cd86dd9f0cb7cf8b7d0fb75e8b1")
    fun getListCurrency():Call<CurrencyModel>
}