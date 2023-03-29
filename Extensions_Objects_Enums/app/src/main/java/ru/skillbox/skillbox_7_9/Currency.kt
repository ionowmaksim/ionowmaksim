package ru.skillbox.skillbox_7_9

enum class Currency(
    val currency: String
) {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");

    companion object {
        const val nationalCurrency = "EUR"
    }
}