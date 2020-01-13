package exercises.reverse

import java.util.*

/*
* https://leetcode.com/problems/reverse-integer/
*/

class Solution {
    fun reverse(x: Int): Int {
        var foo = x
        var rev = 0
        println("foo $foo")
        while (foo != 0) {
            val pop = foo % 10
            foo /= 10
            rev = rev * 10 + pop
            println("$pop $foo $rev")
        }

        var negative = false
        val list = LinkedList<Char>()
        x.toString().forEachIndexed { index, c ->
            if (index == 0 && c == '-') {
                negative = true
            } else {
                list.addFirst(c)
            }
        }

        if (negative) list.addFirst('-')

        return runCatching {
            list.joinToString("").toInt()
        }.getOrElse { 0 }
    }
}

fun main() {
    val test1 = 123 to 321
    val test2 = -123 to -321
    val test3 = 120 to 21
    val test4 = Int.MAX_VALUE to 0
    val test5 = Int.MIN_VALUE to 0

    val tests = listOf(test1, test2, test3, test4, test5)

    val s = Solution()

    tests.forEach {
        val result = s.reverse(it.first)
        println(result)
        if (result != it.second) throw Exception("test case $it failed, result $result, expected ${it.second}")
    }
}