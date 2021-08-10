package com.petruciostech.android_pedrohenriquemacdodasilva.data

import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyModel
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyQuotesModel

interface CurrencyListDataSource {
    fun getAllCurrency():CurrencyModel?

    fun getAllQuotes():CurrencyQuotesModel?
}