package ru.skillbox.skillbox_6_5

class Person(
    val HEIGHT: Int,
    val WEIGHT: Int,
    val NAME: String
) {
    val pets = hashSetOf<Animal>()

    fun buyPet() {
        pets.add(
            Animal(
                ENERGY = (1..100).random(),
                WEIGHT = (1..70).random(),
                NAME = "Pet " + (pets.size + 1).toString()
            )
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (HEIGHT != other.HEIGHT) return false
        if (WEIGHT != other.WEIGHT) return false
        if (NAME != other.NAME) return false
        if (pets != other.pets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = HEIGHT
        result = 31 * result + WEIGHT
        result = 31 * result + NAME.hashCode()
        result = 31 * result + pets.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(height=$HEIGHT, weight=$WEIGHT, name='$NAME', pets=$pets)"
    }
}