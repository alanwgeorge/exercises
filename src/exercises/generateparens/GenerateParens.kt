@file:Suppress("unused")

package exercises.generateparens

/*
* https://leetcode.com/problems/generate-parentheses/
*/

class Solution(val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()

        generateParenthesisRecur(n, n, "", result)

        return result
    }

    private fun generateParenthesisRecur(openCount: Int, closeCount: Int, stringAccum: String, resultsAccum: MutableList<String>) {
        if (debug) println("$openCount $closeCount, $stringAccum, $resultsAccum")
        loops++
        if (openCount == 0 && closeCount == 0) {
            resultsAccum.add(stringAccum)
            return
        }

        if (openCount > closeCount) return
        if (openCount > 0) generateParenthesisRecur(openCount - 1, closeCount, "$stringAccum(", resultsAccum)
        if (closeCount > 0) generateParenthesisRecur(openCount, closeCount - 1, "$stringAccum)", resultsAccum)

    }
}

class Solution2(val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        if (n == 0) {
            result.add("")
        } else {
            for (i in 0 until n) {
                for (left in generateParenthesis(i)) {
                    for (right in generateParenthesis(n - 1 - i)) {
                        loops++
                        result.add("($left)$right")
                        if (debug) println("n:$n left:$left right:$right $result")
                    }
                }
            }
        }

        return result
    }
}

fun main() {
    val tests = listOf(
            3 to listOf("((()))", "(()())", "(())()", "()(())", "()()()"),
            4 to listOf("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"),
            5 to listOf("((((()))))", "(((()())))", "(((())()))", "(((()))())", "(((())))()", "((()(())))", "((()()()))", "((()())())", "((()()))()", "((())(()))", "((())()())", "((())())()", "((()))(())", "((()))()()", "(()((())))", "(()(()()))", "(()(())())", "(()(()))()", "(()()(()))", "(()()()())", "(()()())()", "(()())(())", "(()())()()", "(())((()))", "(())(()())", "(())(())()", "(())()(())", "(())()()()", "()(((())))", "()((()()))", "()((())())", "()((()))()", "()(()(()))", "()(()()())", "()(()())()", "()(())(())", "()(())()()", "()()((()))", "()()(()())", "()()(())()", "()()()(())", "()()()()()")
    )

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        Solution2.loops = 0
        val result = s.generateParenthesis(it.first)
        println("result:$result loops:${Solution2.loops}")
        if (result.sorted() != it.second.sorted()) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}
