package org.vadim.matrix

class Vector(private val size: Int) {
    val data: DoubleArray = DoubleArray(size)

    fun inputVector() {
        for (i in 0 until size) {
            print("Введите элемент вектора b($i): ")
            data[i] = readln().toDouble()
        }
    }

    fun printVector(name: String) {
        println("Вектор $name:")
        for (i in data.indices) {
            println("[$i]: ${"%.2f".format(data[i])}")
        }
    }

    // вычитание векторов (для невязок)
    operator fun minus(other: Vector): Vector {
        val result = Vector(size)
        for (i in 0 until size) {
            result.data[i] = data[i] - other.data[i]
        }
        return result
    }
}