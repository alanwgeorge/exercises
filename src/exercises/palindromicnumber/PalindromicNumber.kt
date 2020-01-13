package exercises.palindromicnumber

/*
* https://leetcode.com/problems/palindrome-number/
*/

class Solution {
    fun isPalindrome(x: Int): Boolean {
        val num = x.toString().toList()
        var start = 0
        var end = num.lastIndex

        while (start < end) {
            if (num[start] != num[end])  return false
            start++
            end--
        }

        return true
    }
}

fun main() {
    val test1 = 121 to true
    val test2 = -121 to false
    val test3 = 10 to false

    val tests = listOf(test1, test2, test3)

    val s = Solution()

    tests.forEach { test ->
        println("test: $test")
        val result = s.isPalindrome(test.first)
        println(result)
        if (result != test.second) throw Exception("test failed $test expected ${test.second} actual $result")
    }
}