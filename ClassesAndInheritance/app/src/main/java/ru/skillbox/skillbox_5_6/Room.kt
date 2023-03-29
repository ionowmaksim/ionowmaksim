package ru.skillbox.skillbox_5_6

open class Room(private val area: Double) {
    protected open val title = "Обычная комната"

    fun getDescription() = println("Название комнаты: $title. Размер: $area m^2.")
}