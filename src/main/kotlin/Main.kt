package org.vadim

import org.vadim.lufact.LUSolver
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

    val solver = LUSolver(size)

    // LU-разложение
    solver.luDecomposition(aMatrix)
    solver.printLU()

    // Решение системы
    val solution = solver.solve(aMatrix, bVector)
    solution.printVector("Решение x")
}