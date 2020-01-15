package exercises.patternmatch

/*
* https://leetcode.com/problems/regular-expression-matching/
*/

class Solution {
    fun isMatch(s: String, p: String): Boolean {
        if (p.isEmpty()) return s.isEmpty().also { val s = if (it) "empty" else "not empty"; println("$it, p was empty, s was $s") }

        var sPtr = 0
        var pPtr = 0

        while (pPtr <= p.lastIndex) {
            val pElement = p[pPtr]
            val dot = pElement == '.'
            val star = p.getOrNull(pPtr + 1)?.equals('*') ?: false
            val dotStarNext =  p.getOrNull(pPtr + 2)

            var sElement: Char? = s.getOrNull(sPtr) ?: run { return false.also { println("false, s ended with more p to process") } }

            if (star) {
                if (dot) {
                    dotStarNext?.let {
                        while (sElement != it && sPtr <= s.lastIndex) {
                            sPtr++
                            sElement = s.getOrNull(sPtr)
                        }
                    } ?: return true.also { println("true, .* last pattern") }
                }

                while (sElement == pElement) {
                    sPtr++
                    sElement = s.getOrNull(sPtr)
                }
                //if the next p is same as current p when processing *, skip it
                while (p[pPtr] == p.getOrNull(pPtr + 2)) pPtr++
                pPtr += 2

            } else if (dot || pElement == sElement) {
                sPtr++
                pPtr++
                continue
            }
        }

        if (sPtr <= s.lastIndex) return false.also { println("false, p ended with more s to process") }

        return true.also { println("true, made it to the end of both s and p") }
    }
}

class Solution2 {
    fun isMatch(text: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return text.isEmpty().also { val s = if (it) "empty" else "not empty"; println("$it, p was empty, s was $s") }

        val firstMatch = (text.isNotEmpty() && ((text[0] == pattern[0] || pattern[0] == '.')))

        println("$text $pattern $firstMatch")

        return if (pattern.length >= 2 && pattern[1] == '*') {
            (isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern)))
        } else {
            (firstMatch && isMatch(text.substring(1), pattern.substring(1)))
        }
    }
}

data class Test(val input: String, val pattern: String, val solution: Boolean)

@Suppress("SpellCheckingInspection")
fun main() {
    val test1 = Test("aa", "a", false)
    val test2 = Test("aa", "a*", true)
    val test3 = Test("aa", ".*", true)
    val test4 = Test("aab", "c*a*b", true)
    val test5 = Test("mississippi", "mis*is*p*.", false)
    val test6 = Test("aa", "aa", true)
    val test7 = Test("aa", "aa*", true)
    val test8 = Test("ab", ".*c", false)
    val test9 = Test("asdsabc", ".*c", true)
    val test10 = Test("aaa", "a*a", true)
    val test11 = Test("", "", true)
    val test12 = Test("a", "", false)
    val test13 = Test("", "a*", false)
    val test14 = Test("aaa", "ab*a*c*a", true)

    val tests = listOf(test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11, test12, test14)

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        val result = s.isMatch(it.input, it.pattern)
        println(result)
        if (result != it.solution) throw Exception("test case $it failed, result $result, expected ${it.solution}")
    }
}