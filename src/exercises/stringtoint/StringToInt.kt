package exercises.stringtoint

/*
* https://leetcode.com/problems/string-to-integer-atoi/
*/

class Solution {
    fun myAtoi(str: String): Int {
        var isNegative = false
        var lastChar = ' '
        var result = 0
        var numberStarted = false

        for (c in str) {
            if (!c.isDigit() && numberStarted) break

            if (c in listOf(' ', '+', '-')) {
                if (lastChar in listOf('+', '-')) return 0
                lastChar = c
                continue
            }

            if (!c.isDigit() && (c != '+' || c != '-')) return result

            numberStarted = true

            if (result == 0 && lastChar == '-') isNegative = true

            val value = c.toString().toInt() * if (isNegative) -1 else 1

            if (isNegative && (result < Int.MIN_VALUE / 10) || (result == Int.MIN_VALUE / 10 && value < -8)) {
                return Int.MIN_VALUE
            } else if (!isNegative && (result > Int.MAX_VALUE / 10) || (result == Int.MAX_VALUE / 10 && value > 7)) {
                return Int.MAX_VALUE
            }

//            val previousResult = result

            if (result != 0) result *= 10
            result += value

//            println("$value $previousResult $result")

        }
        return result
    }
}

fun main() {

    val test1 = "42" to 42
    val test2 = "   -42" to -42
    val test3 = "4193 with words" to 4193
    val test4 = "words and 987" to 0
    val test5 = "-91283472332" to Int.MIN_VALUE
    val test6 = "+91283472332" to Int.MAX_VALUE
    val test7 = "2147483648" to Int.MAX_VALUE
    val test8 = "+-2" to 0
    val test9 = "-6147483648" to Int.MIN_VALUE
    val test10 = "-2147483649" to Int.MIN_VALUE
    val test11 = "0-1" to 0

    val tests = listOf(test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11)

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.myAtoi(it.first)
        println(result)
        if (result != it.second) throw Exception("test case $it failed, result $result, expected ${it.second}")
    }
}