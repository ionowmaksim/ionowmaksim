package ru.skillbox.skillbox_7_9

val Currency.isNationalCurrency: Boolean
    get() {
        return this.currency == Currency.nationalCurrency
    }

fun Currency.convertToUSD(countCurrency: Double): Double {
    return when (this) {
        Currency.RUB -> countCurrency / CurrencyConverter.RUB
        Currency.EUR -> countCurrency / CurrencyConverter.EUR
        Currency.USD -> countCurrency
    }
}