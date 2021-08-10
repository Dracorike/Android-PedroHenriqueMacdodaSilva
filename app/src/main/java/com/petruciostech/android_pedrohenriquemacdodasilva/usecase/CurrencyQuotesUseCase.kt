package com.petruciostech.android_pedrohenriquemacdodasilva.usecase

import com.petruciostech.android_pedrohenriquemacdodasilva.data.CurrencyRepository

class CurrencyQuotesUseCase(private val repository: CurrencyRepository) {
    operator fun invoke() = repository.getAllQuotes()
}