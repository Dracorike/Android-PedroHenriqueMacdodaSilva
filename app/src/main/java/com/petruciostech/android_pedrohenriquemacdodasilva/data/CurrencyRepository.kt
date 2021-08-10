package com.petruciostech.android_pedrohenriquemacdodasilva.data

class CurrencyRepository(private val dataSource: CurrencyListDataSource) {
    fun getAllCurrency() = dataSource.getAllCurrency()
    fun getAllQuotes() = dataSource.getAllQuotes()
}