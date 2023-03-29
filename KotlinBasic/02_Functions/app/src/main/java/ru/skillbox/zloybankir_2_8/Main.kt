package ru.skillbox.zloybankir_2_8

import kotlin.math.sqrt

fun main() {
    val solutionSum = solveEquation(a = 1, b = 1, c = 3)
    println(solutionSum)
}

fun solveEquation (a: Int, b: Int, c: Int): Any {
    // Calculating the discriminant
    val d = b * b - (4 * a * c)

    // Calculating the sqrt
    val x1 = (-b + sqrt(d.toFloat())) / (2 * a)
    val x2 = (-b - sqrt(d.toFloat())) / (2 * a)

    return if (d < 0) "Discriminant < 0" else x1 + x2
}