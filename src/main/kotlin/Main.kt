package org.vadim

import org.vadim.matrix.Matrix
import org.vadim.matrix.Vector

fun main(args: Array<String>) {
    val size = 3
    val aMatrix = Matrix(size)
    val bVector = Vector(size)

    println("Ввод матрицы:")
    aMatrix.inputMatrix()

    println("Ввод вектора b:")
    bVector.inputVector()
}