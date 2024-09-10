package org.vadim

import org.vadim.lufact.LUSolver
import org.vadim.matrix.Matrix
import org.vadim.matrix.Vector

fun main(args: Array<String>) {
    print("Размерность матрицы A:")
    val size = readln().toInt()

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

    println("|A| = ${solver.determinant()}")
    val inverseMatrix = solver.inverse(aMatrix)
    inverseMatrix.printMatrix("A^-1")

    // Решение системы
    val solution = solver.solve(aMatrix, bVector)
    solution.printVector("Решение x")

    val residual = aMatrix * solution - bVector
    residual.printVector("Невязки")

    val aNorm = aMatrix.norm()
    val aInverseNorm = inverseMatrix.norm()

    //k(A) = ||A|| * ||A^-1||
    //чем обусловленость (чувствительность) меньше, тем лучше
    println("Обусловленность A = %.2f".format(aNorm * aInverseNorm))

}