package lagrange_interpolation

import kotlin.math.abs

class LagrangeInterpolation(
    private val X: List<Double>,
    private val Y: List<Double>,
    private val EPS: Double
) {
    private val N: Int = X.size

    private fun lagrangePolynomial(x: Double, points: List<Pair<Double, Double>>): Double {
        var lagrangeSum = 0.0
        for (i in points.indices) {
            var term = points[i].second
            for (j in points.indices)
                if (j != i)
                    term *= (x - points[j].first) / (points[i].first - points[j].first)
            lagrangeSum += term
        }
        return lagrangeSum
    }

    fun interpolate(XX: Double): Pair<Double?, Int> {
        if (Y.size != N || N < 2)
            return Pair(null, 1)  // Ошибка: размеры векторов не совпадают
        if (X.zipWithNext().any { it.first >= it.second })
            return Pair(null, 3)  // Ошибка: нарушен порядок возрастания аргументов
        if (!(X.first() <= XX && XX <= X.last()))
            return Pair(null, 4)  // Ошибка: значение аргумента XX вне диапазона

        var prevValue: Double? = null
        var iterations = 0

        var closestPoints = listOf<Pair<Double, Double>>()

        while (true) {
            closestPoints = X.mapIndexed { index, x -> Pair(x, Y[index]) }
                .sortedBy { abs(it.first - XX) }
                .take(2 + iterations) // Увеличиваем количество ближайших точек с каждой итерацией

            val YY = lagrangePolynomial(XX, closestPoints)

            if (prevValue != null && abs(YY - prevValue) < EPS)
                return Pair(YY, 0)  // Требуемая точность достигнута
            if (iterations >= N - 1)
                return Pair(YY, 2)  // Достигнут предел итераций

            prevValue = YY
            iterations++
        }
    }
}