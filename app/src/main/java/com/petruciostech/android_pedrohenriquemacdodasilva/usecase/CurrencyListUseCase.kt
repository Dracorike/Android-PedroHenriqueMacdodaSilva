package com.petruciostech.android_pedrohenriquemacdodasilva.usecase

import com.petruciostech.android_pedrohenriquemacdodasilva.data.CurrencyRepository

class CurrencyListUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke() = currencyRepository.getAllCurrency()
}