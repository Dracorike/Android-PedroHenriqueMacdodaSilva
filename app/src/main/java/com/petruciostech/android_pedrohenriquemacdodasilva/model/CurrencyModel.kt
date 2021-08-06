package com.petruciostech.android_pedrohenriquemacdodasilva.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("currencies") val listCurrency:MutableMap<String, String>
)