package ru.skillbox.skillbox_7_9

fun main() {
    val realWallet = Wallets.RealWallet()
    realWallet.addMoney(
        inputCurrency = Currency.RUB,
        denomination = 1.0,
        numberOfCurrency = 10
    )
    realWallet.addMoney(
        inputCurrency = Currency.EUR,
        denomination = 100.0,
        numberOfCurrency = 5
    )

    val virtualWallet = Wallets.VirtualWallet()
    virtualWallet.addMoney(inputCurrency = Currency.EUR, numberOf = 100.0)
    virtualWallet.addMoney(inputCurrency = Currency.USD, numberOf = 5.0)

    println(
        "VirtualWallet ${
            if (virtualWallet.moneyInUsd() > realWallet.moneyInUsd()) ">" else
                if (virtualWallet.moneyInUsd() < realWallet.moneyInUsd()) "<" else "="
        } RealWallet\n" +
                "VirtualWallet: ${virtualWallet.moneyInUsd()} USD\n" +
                "RealWallet: ${realWallet.moneyInUsd()} USD"
    )
}

object CurrencyConverter {
    const val RUB = 73.10
    const val EUR = 0.84
}