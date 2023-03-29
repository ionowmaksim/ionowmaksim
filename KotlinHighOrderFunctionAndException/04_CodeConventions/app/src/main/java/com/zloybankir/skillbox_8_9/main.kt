package com.zloybankir.skillbox_8_9

fun main() {
    val testWheel = Wheel()
    val testWheel2 = Wheel()
    val testWheel3 = Wheel()

    try {
        testWheel.setPressure(7.4)
        testWheel.check()
//        testWheel2.setPressure(2.0)
//        testWheel2.check()
//        testWheel3.setPressure(-4.2)
//        testWheel3.check()
    } catch (ex: Wheel.IncorrectPressure) {
        println(ex.message)
        println("Колесо не было накачано.")
    } catch (ex: Wheel.TooLowPressure) {
        println(ex.message)
        println("Колесо нельзя эксплуатировать.")
    } catch (ex: Wheel.TooHightPressure) {
        println(ex.message)
        println("Колесо нельзя эксплуатировать.")
    }
}

class Wheel() {
    private var pressure: Double = 0.0

    fun getPressure(): Double {
        return pressure
    }

    fun setPressure(value: Double) {
        if (value < 0 || value > 10) throw IncorrectPressure()
        pressure = value
        println("Колесо успешно накачано.")
    }

    fun check() {
        if (pressure > 2.5) throw TooHightPressure()
        if (pressure < 1.6) throw TooLowPressure()
        println("Давление в норме, колесо можно эксплуатировать.")
    }

    class TooHightPressure(message: String = "Давление выше нормы.") : Exception(message)

    class TooLowPressure(message: String = "Давление ниже нормы.") : Exception(message)

    class IncorrectPressure(message: String = "Недопустимое давление.") : Exception(message)
}
