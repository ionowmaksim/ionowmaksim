package ru.skillbox.gitlab_3_7

import kotlin.math.abs

fun main() {
    val listOfNumbers = readingNNumbers(readingIntNumber(message = "Введите кол-во чисел: "))

    println("Кол-во положительных чисел: ${countingPositiveNumbers(listOfNumbers)}")
    val firstNumber = sumList(listOfNumbers)
    val secondNumber = readingIntNumber("Введите число для вычисления НОД: ")
    print("НОД $firstNumber и $secondNumber равен ${greatestCommonDivisor(firstNumber, secondNumber)}")
}

fun readingIntNumber(message: String = "Введите целое число: "): Int {
    while (true) {
        print(message)
        readLine()?.toIntOrNull()
                ?.let { return it }
                ?: println("Вы ввели не целое число!")
    }
}

fun readingNNumbers(count: Int) =
        mutableListOf<Int>().apply {
            var counter = 1
            while (counter <= count) {
                print("$counter-е число: ")
                readLine()?.toIntOrNull()
                        ?.let {
                            add(it)
                            counter++
                        }
                        ?: println("Вы ввели не целое число!")
            }
        }

fun countingPositiveNumbers(list: List<Int>) = list.filter { it > 0 }.count()

fun sumList(list: List<Int>): Int {
    var sum = 0
    list.forEach { sum += it }
    return sum
}

tailrec fun greatestCommonDivisor(a: Int, b: Int): Int {
    if (b == 0) return abs(a)
    return greatestCommonDivisor(b, a % b)
}