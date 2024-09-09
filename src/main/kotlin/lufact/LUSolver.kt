package org.vadim.lufact

import org.vadim.matrix.Matrix
import org.vadim.matrix.Vector

class LUSolver(private val size: Int) {
    private val L = Matrix(size)
    private val U = Matrix(size)

    fun luDecomposition(aMatrix: Matrix) {
        for (i in 0 until size) {
            for (j in i until size) {
                U.data[i][j] = aMatrix.data[i][j] // Заполняем верхний треугольник U
                var sum = 0.0
                for (k in 0 until i) {
                    sum += L.data[i][k] * U.data[k][j]
                }
                U.data[i][j] = aMatrix.data[i][j] - sum
            }

            for (j in i until size) {
                if (i == j) {
                    L.data[i][i] = 1.0 // Диагональные элементы L равны 1
                } else {
                    var sum = 0.0
                    for (k in 0 until i) {
                        sum += L.data[j][k] * U.data[k][i]
                    }
                    L.data[j][i] = (aMatrix.data[j][i] - sum) / U.data[i][i]
                }
            }
        }
    }

    fun solve(aMatrix: Matrix, b: Vector): Vector {
        val y = DoubleArray(size)
        val x = DoubleArray(size)

        // Прямой ход (решаем LY = B)
        for (i in 0 until size) {
            var sum = 0.0
            for (j in 0 until i) {
                sum += L.data[i][j] * y[j]
            }
            y[i] = b.data[i] - sum
        }

        // Обратный ход (решаем UX = Y)
        for (i in size - 1 downTo 0) {
            var sum = 0.0
            for (j in i + 1 until size) {
                sum += U.data[i][j] * x[j]
            }
            x[i] = (y[i] - sum) / U.data[i][i]
        }

        val result = Vector(size)
        for (i in 0 until size) {
            result.data[i] = x[i]
        }

        return result
    }

    fun printLU() {
        L.printMatrix("L")
        U.printMatrix("U")
    }
}