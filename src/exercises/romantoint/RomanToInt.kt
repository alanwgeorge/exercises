package exercises.romantoint

/*
* https://leetcode.com/problems/roman-to-integer/
*/

class Solution {
    fun romanToInt(s: String): Int {
        var result = 0

        for (c in s.reversed()) {
            when (c) {
                'I' -> if (result >= 5) result-- else result++
                'V' -> result += 5
                'X' -> if (result >= 50) result -= 10 else result += 10
                'L' -> result += 50
                'C' -> if (result >= 500) result -= 100 else result += 100
                'D' -> result += 500
                'M' -> result += 1000
            }
        }

        return result
    }
}

fun main() {
    @Suppress("SpellCheckingInspection")
    val tests = listOf(
            "III" to 3,
            "IV" to 4,
            "IX" to 9,
            "LVIII" to 58,
            "MCMXCIV" to 1994,
            "MMMCMXCIX" to 3999
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.romanToInt(it.first)
        println(result)
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}