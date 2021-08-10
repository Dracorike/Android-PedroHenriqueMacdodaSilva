package com.petruciostech.android_pedrohenriquemacdodasilva.model

import com.google.gson.annotations.SerializedName

data class CurrencyQuotesModel(
    @SerializedName("quotes") val quotes:MutableMap<String, Double>
)