package exercises.validparens

import java.util.*

/*
* https://leetcode.com/problems/valid-parentheses/
*/

@Suppress("SpellCheckingInspection")
class Solution {
    fun isValid(s: String): Boolean {
        val stack = LinkedList<Char>()

        s.forEach { c ->
            when (c) {
                '(' -> stack.addFirst(c)
                '{' -> stack.addFirst(c)
                '[' -> stack.addFirst(c)
                ')' -> if (stack.pollFirst() != '(') return false
                '}' -> if (stack.pollFirst() != '{') return false
                ']' -> if (stack.pollFirst() != '[') return false
            }
        }

        return stack.isEmpty()
    }
}

fun main() {
    val tests = listOf(
            "()" to true,
            "(){}[]" to true,
            "{[]}" to true,
            "(]" to false,
            "([)]" to false
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.isValid(it.first)
        println(result)
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}