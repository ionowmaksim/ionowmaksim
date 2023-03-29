package com.zloybankir.skillbox_8_5

fun main() {
    val queue = MyQueue<String>("1st string: This is text message 1!")
    queue.enqueue("2st string: Null")
    queue.enqueue("3st string: This is text message 3!")

    val myLambdaFunction = { item: String -> item.length >= 20 }

    val newQueue = queue.filter { myLambdaFunction(it) }
    newQueue.printText()
}

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

    fun filter(selector: (T) -> Boolean): MyQueue<T> {
        val filteredQueue = mutableListOf<T>()
        myQueue.forEach { item -> if (selector(item)) filteredQueue.add(item) }
        val returnQueue = MyQueue<T>(filteredQueue[0])
        for (i in 1..filteredQueue.lastIndex) {
            returnQueue.enqueue(filteredQueue[i])
        }
        return returnQueue
    }

    fun printText() {
        myQueue.forEach { println(it) }
    }
}