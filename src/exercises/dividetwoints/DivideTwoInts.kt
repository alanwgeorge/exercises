package exercises.dividetwoints

import kotlin.math.abs

/*
* https://leetcode.com/problems/divide-two-integers/
*/

class Solution {
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MAX_VALUE && divisor == Int.MIN_VALUE) return 0

        val sign = if ((dividend < 0) xor (divisor < 0)) -1 else 1

        var dividendAbs = if (dividend == Int.MIN_VALUE) Int.MAX_VALUE else abs(dividend)
        val divisorAbs = if (divisor == Int.MIN_VALUE) Int.MAX_VALUE else abs(divisor)

        var quotient = 0

        while (dividendAbs >= divisorAbs) {
            dividendAbs -= divisorAbs
            quotient++
        }

        val result = sign * quotient

        return if (dividend == Int.MIN_VALUE && sign == -1) result - 1 else result
    }
}

class Solution2 {
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
        var dividendAbs: Long = Math.abs(dividend.toLong())
        val divisorAbs: Long = Math.abs(divisor.toLong())
        val sign = (if (dividend < 0) -1 else 1) * (if (divisor < 0) -1 else 1)
        var cnt = 0
        while (dividendAbs >= divisorAbs) {
            dividendAbs -= divisorAbs
            cnt++
        }
        return cnt * sign
    }
}

data class Test(val dividend: Int, val divisor: Int, val solution: Int)

fun main() {
    val tests = listOf(
            Test(10, 3, 3),
            Test(7, -3, -2),
            Test(43, -8, -5),
            Test(5, 10, 0),
            Test(-2147483648, -1, 2147483647),
            Test(-2147483648, 1, -2147483648),
            Test(-1010369383, -2147483648, 0),
            Test(2147483647, -2147483648, 0)
    )

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        val result = s.divide(it.dividend, it.divisor)
        println(result)
        if (result != it.solution) throw Exception("test $it failed, result $result, expected ${it.solution}")
    }
}