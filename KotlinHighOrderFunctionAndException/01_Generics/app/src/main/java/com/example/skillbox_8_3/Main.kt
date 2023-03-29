package com.example.skillbox_8_3

import kotlin.random.Random

fun main() {
    //Задание 1
    val listItems = listOf(431, 233, 2, 14, 47, -12)
    val listItems2 = listOf(-44.2, 37.1, 45.0, -18.0, 18.8, 56.3, 44.0)
//    val listItems3 = listOf<Any>()

    println(genericFilter(listItems))
    println(genericFilter(listItems2))
//    println(genericFilter(listItems3)) // ошибка, только подтипы Number

    //Задание 2
    val queue = MyQueue<String>("1st string")
    queue.enqueue("2st string")
    queue.enqueue("3st string")
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())

    //Задание 3
    val a1: Result<Number, String> = returned()
    val a2: Result<Number, String> = returned()
    val b1: Result<Any, String> = returned()
    val b2: Result<Any, String> = returned()

//    var c1 : Result<Int, CharSequence> = returned()
//    var c2 : Result<Int, CharSequence> = returned()
//    var d1 : Result<Int, Any> = returned()
//    var d2 : Result<Int, Any> = returned()
}

//Задание 1
fun <T : Number> genericFilter(list: List<T>): List<T> {
    val returnList = mutableListOf<T>()
    list.forEach { if(it.toDouble() % 2 == 0.0) returnList.add(it) }
    return returnList
}

//Задание 2
class MyQueue<T>(startValue: T) {
    private val myQueue = mutableListOf<T>(startValue)

    fun enqueue(item: T) {
        myQueue.add(item)
    }

    fun dequeue(): T? {
        if (myQueue.isEmpty()) return null
        val returnItem = myQueue[0]
        myQueue.removeAt(0)
        return returnItem
    }
}

//Задание 3
sealed class Result<out T, R> {
    data class Success<T, R>(var success: T) : Result<T, R>()
    data class Error<T, R>(var error: R) : Result<T, R>()
}

fun returned(): Result<Int, String> {
    val success: Result<Int, String> = Result.Success(100)
    val error: Result<Int, String> = Result.Error("Error!!!")
    return if (Random.nextBoolean()) success else error
}
