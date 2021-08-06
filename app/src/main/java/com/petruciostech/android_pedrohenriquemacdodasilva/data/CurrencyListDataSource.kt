package com.petruciostech.android_pedrohenriquemacdodasilva.data

import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyModel

interface CurrencyListDataSource {
    fun getAllCurrency():CurrencyModel?
}