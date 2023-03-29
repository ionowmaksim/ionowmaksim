package ru.skillbox.zloybankir_2_5

fun main() {
    val firstName = "Maksim"
    val lastName = "Ionou"
    val isMan = true
    var height = 187
    var weight = 67.5f
    var isChild = (height < 150 || weight < 40)
    var info: String = "${if (isMan) "His" else "Her"} name is $firstName $lastName. " +
            "${if (isMan) "His" else "Her"} heaight $height, and ${if (isMan) "his" else "her"} " +
            "weight $weight. ${if (isMan) "He" else "She"} is ${if (isChild) "a child" else "an adult"}."
    println(info)
    height = 95
    weight = 39f
    isChild = (height < 150 || weight < 40)
    info = "${if (isMan) "His" else "Her"} name is $firstName $lastName. " +
            "${if (isMan) "His" else "Her"} heaight $height, and ${if (isMan) "his" else "her"} " +
            "weight $weight. ${if (isMan) "He" else "She"} is ${if (isChild) "a child" else "an adult"}."
    print(info)
}