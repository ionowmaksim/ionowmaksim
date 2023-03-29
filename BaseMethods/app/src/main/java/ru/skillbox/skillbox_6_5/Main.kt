package ru.skillbox.skillbox_6_5

fun main() {
    val person1 = Person(HEIGHT = 40, WEIGHT = 180, NAME = "Dmitriy")
    val person2 = Person(HEIGHT = 40, WEIGHT = 180, NAME = "Dmitriy")
    val person3 = Person(HEIGHT = 70, WEIGHT = 160, NAME = "Oleg")

    val setOfPersons = mutableSetOf(person1, person2, person3)
    setOfPersons.forEach {
        it.buyPet()
        it.buyPet()
    }

    println(setOfPersons.size)
    setOfPersons.forEach { println(it) }
}