package ru.skillbox.skillbox_4_5

fun main() {
    val mutableTelephoneList = mutableListOf<String>()

    while (true) {
        print("Укажите кол-во вводимых номеров (числом): ")
        val n = readLine()?.toShortOrNull()
        if (n != null) {
            inputNumbersFromUser(n, mutableTelephoneList)
            break
        } else {
            println("Вы ввели не число!!!")
        }
    }

    filterNumbers(mutableTelephoneList)
    printUniqueNumbers(mutableTelephoneList)
    printSumNumbers(mutableTelephoneList)

    convertToMutableMap(mutableTelephoneList).forEach() {
        println("Человек: ${it.value}. Номер телефона: ${it.key}")
    }
}

fun inputNumbersFromUser(n: Short, list: MutableList<String>) {
    for (i in 1..n) {
        print("Введите $i-й номер телефона (+7***): ")
        readLine().toString().let { list.add(it) }
    }
}

fun filterNumbers(list: MutableList<String>) {
    for (i in list.filter { it.substring(0, 2) != "+7" }) list.remove(i)
}

fun printUniqueNumbers(list: MutableList<String>) {
    println("${list.toSet().size} уникальных номеров.")
}

fun printSumNumbers(list: MutableList<String>) {
    println("Сумма длинн всех номеров: ${list.sumBy { it.length - 1 }}") //length without +
}

fun convertToMutableMap(list: MutableList<String>) = mutableMapOf<String, String>().apply {
    list.forEach() {
        print("Введите имя человека с номером телефона $it: ")
        put(it, readLine().toString())
    }
}