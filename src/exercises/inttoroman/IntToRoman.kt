package exercises.inttoroman

import java.util.*

/*
* https://leetcode.com/problems/integer-to-roman/
*/

class Solution {
    fun intToRoman(num: Int): String {
        var _num = num
        val digits = mutableListOf<Int>()
        while (_num > 0) {
            digits.add(_num % 10)
            _num /= 10
        }

        fun getChars(value: Int, signif: Int): String =
            when {
                value == 9 -> when (signif) {
                    0 -> "IX"
                    1 -> "XC"
                    2 -> "CM"
                    else -> throw Exception("invalid value of signif=$signif value=$value")
                }
                value <= 3 -> when (signif) {
                    0 -> "I"
                    1 -> "X"
                    2 -> "C"
                    3 -> "M"
                    else -> throw Exception("invalid value of signif=$signif value=$value")
                }.repeat(value)
                value >= 5 -> when (signif) {
                    0 -> "V"
                    1 -> "L"
                    2 -> "D"
                    else -> throw Exception("invalid value of signif=$signif value=$value")
                }
                value == 4 -> when (signif) {
                    0 -> "IV"
                    1 -> "XL"
                    2 -> "CD"
                    else -> throw Exception("invalid value of signif=$signif value=$value")
                }
                else -> throw Exception("invalid value of signif=$signif value=$value")
            }

        val result = LinkedList<String>()
        digits.forEachIndexed { index, i ->
            var temp = i
            var chars = getChars(temp, index)
            while (temp > 0) {
                if (temp == 9) {
                    temp = 0
                } else if (temp <= 3) {
                    if (i >= 5) chars += getChars(temp, index)
                    temp = 0
                } else if (temp >= 5) {
                    temp -= 5
                } else if (temp == 4) {
                    temp = 0
                }
            }
            result.addFirst(chars)
        }

        return result.joinToString("")
    }
}

class Solution2() {
    private val romanThresholds = mapOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
    )

    fun intToRoman(num: Int): String {
        var _num = num
        var result = ""

        for ((threshold, roman) in romanThresholds) {
            while (_num / threshold > 0) {
                _num -= threshold
                result += roman
            }
        }

        return result
    }
}


fun main() {
    @Suppress("SpellCheckingInspection")
    val tests = listOf(
            3 to "III",
            4 to "IV",
            9 to "IX",
            88 to "LXXXVIII",
            40 to "XL",
            58 to "LVIII",
            1994 to "MCMXCIV",
            3999 to "MMMCMXCIX"
    )

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        val result = s.intToRoman(it.first)
        println(result)
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}