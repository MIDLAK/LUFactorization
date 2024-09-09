package org.vadim.matrix

class Matrix(private val size: Int) {
    val data: Array<DoubleArray> = Array(size) { DoubleArray(size) }

    fun inputMatrix() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                print("Введите элемент матрицы A($i, $j): ")
                data[i][j] = readln().toDouble()
            }
        }
    }

    fun printMatrix(name: String) {
        println("Матрица $name:")
        for (row in data) {
            for (value in row) {
                print("${"%.2f".format(value)}\t")
            }
            println()
        }
    }
}