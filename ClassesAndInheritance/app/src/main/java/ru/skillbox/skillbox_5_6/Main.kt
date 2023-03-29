package ru.skillbox.skillbox_5_6

fun main() {
    arrayOf<Room>(
            Bedroom(15.0),
            LivingRoom(17.0),
            Kitchen(15.0),
            Bathroom(7.0),
            UniversalRoom(15.0, "Комната для работы"),
            UniversalRoom(17.0, "Книжная комната")
    ).forEach() { it.getDescription() }
}