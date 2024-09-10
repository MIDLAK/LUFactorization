package org.vadim.lufact

import org.vadim.matrix.Matrix
import org.vadim.matrix.Vector

class LUSolver(private val size: Int) {
    private val lower = Matrix(size)
    private val upper = Matrix(size)

    fun luDecomposition(aMatrix: Matrix) {
        for (i in 0 until size) {
            for (j in i until size) {
                upper.data[i][j] = aMatrix.data[i][j] // Заполняем верхний треугольник U
                var sum = 0.0
                for (k in 0 until i) {
                    sum += lower.data[i][k] * upper.data[k][j]
                }
                upper.data[i][j] = aMatrix.data[i][j] - sum
            }

            for (j in i until size) {
                if (i == j) {
                    lower.data[i][i] = 1.0 // Диагональные элементы L равны 1
                } else {
                    var sum = 0.0
                    for (k in 0 until i) {
                        sum += lower.data[j][k] * upper.data[k][i]
                    }
                    lower.data[j][i] = (aMatrix.data[j][i] - sum) / upper.data[i][i]
                }
            }
        }
    }

    fun solve(aMatrix: Matrix, b: Vector): Vector {
        val y = DoubleArray(size)
        val x = DoubleArray(size)

        // прямой ход (LY = B)
        for (i in 0 until size) {
            var sum = 0.0
            for (j in 0 until i) {
                sum += lower.data[i][j] * y[j]
            }
            y[i] = b.data[i] - sum
        }

        // обратный ход (UX = Y)
        for (i in size - 1 downTo 0) {
            var sum = 0.0
            for (j in i + 1 until size) {
                sum += upper.data[i][j] * x[j]
            }
            x[i] = (y[i] - sum) / upper.data[i][i]
        }

        val result = Vector(size)
        for (i in 0 until size) {
            result.data[i] = x[i]
        }

        return result
    }

    fun printLU() {
        lower.printMatrix("L")
        upper.printMatrix("U")
    }

    // |A| = произведение диагонали U (upper)
    fun determinant(): Double {
        var det = 1.0
        for (i in 0 until size) {
            det *= upper.data[i][i]
        }

        return det
    }

    // A*X_i = e_i
    // X_i - столбец обратной матрицы
    // e_i - столбец единичной матрицы
    fun inverse(matrix: Matrix): Matrix {
        val inverse = Matrix(size)
        for (i in 0 until size) {
            val e = Vector(size)
            e.data[i] = 1.0
            val xColumn = solve(matrix, e)

            // запись столбца решения
            for (j in 0 until size) {
                inverse.data[j][i] = xColumn.data[j]
            }
        }

        return inverse
    }
}