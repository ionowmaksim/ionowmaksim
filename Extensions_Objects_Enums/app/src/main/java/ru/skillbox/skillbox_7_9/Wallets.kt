package ru.skillbox.skillbox_7_9

sealed class Wallets {
    abstract fun moneyInUsd(): Double

    class VirtualWallet : Wallets() {
        private var numberOfUSD = 0.0
        private var numberOfEUR = 0.0
        private var numberOfRUB = 0.0

        fun addMoney(inputCurrency: Currency, numberOf: Double) {
            when (inputCurrency) {
                Currency.RUB -> numberOfRUB += numberOf
                Currency.USD -> numberOfUSD += numberOf
                Currency.EUR -> numberOfEUR += numberOf
            }
        }

        override fun moneyInUsd(): Double {
            return numberOfUSD + Currency.EUR.convertToUSD(numberOfEUR) + Currency.RUB.convertToUSD(
                numberOfRUB
            )
        }
    }

    class RealWallet : Wallets() {
        private val numberOfUSD = mutableMapOf<Double, Int>()
        private val numberOfEUR = mutableMapOf<Double, Int>()
        private val numberOfRUB = mutableMapOf<Double, Int>()

        fun addMoney(inputCurrency: Currency, denomination: Double, numberOfCurrency: Int) {
            when (inputCurrency) {
                Currency.RUB -> {
                    numberOfRUB[denomination]?.let {
                        numberOfRUB.put(denomination, it + numberOfCurrency)
                    }
                        ?: numberOfRUB.put(denomination, numberOfCurrency)
                }
                Currency.USD -> {
                    numberOfUSD[denomination]?.let {
                        numberOfUSD.put(denomination, it + numberOfCurrency)
                    }
                        ?: numberOfUSD.put(denomination, numberOfCurrency)
                }
                Currency.EUR -> {
                    numberOfEUR[denomination]?.let {
                        numberOfEUR.put(denomination, it + numberOfCurrency)
                    }
                        ?: numberOfEUR.put(denomination, numberOfCurrency)
                }
            }
        }

        override fun moneyInUsd(): Double {
            var usd = 0.0
            numberOfUSD.forEach { denomination, count -> usd += denomination * count }
            numberOfRUB.forEach { denomination, count ->
                usd += Currency.RUB.convertToUSD(denomination * count)
            }
            numberOfEUR.forEach { denomination, count ->
                usd += Currency.EUR.convertToUSD(denomination * count)
            }
            return usd
        }
    }
}
