import lagrange_interpolation.LagrangeInterpolation

fun main() {
    val X = listOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0) // Значения аргументов
    val Y = X.map { it * it * it } // Значения функции y = x^2 в узлах интерполяции
    //val Y = listOf(100.0, 100.0, 100.0, 9.0, 16.0, 25.0, 36.0)
    //val Y = listOf(100.0, 1000.0, 8.0, 27.0, 64.0, 125.0, 1000.0)
    val XX = 4.1 // Значение аргумента, для которого мы хотим интерполировать функцию
    val EPS = 0.01 // Значение верхней границы абсолютной погрешности

    val interpolator = LagrangeInterpolation(X, Y, EPS)
    val (YY, IER) = interpolator.interpolate(XX)
    println("Интерполяционное значение: $YY")
    println("Индикатор ошибки: $IER")
}
