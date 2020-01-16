package exercises.patternmatch


/*
* https://leetcode.com/problems/regular-expression-matching/
*/

@Suppress("unused")
class Solution1 { // broken
    fun isMatch(s: String, p: String): Boolean {
        if (p.isEmpty()) return s.isEmpty().also { val e = if (it) "empty" else "not empty"; println("$it, p was empty, s was $e") }

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

@Suppress("unused", "MemberVisibilityCanBePrivate")
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

class Solution3(private val debug:Boolean = false) {
    fun isMatch(text: String, pattern: String): Boolean {
        val memo = List(text.length + 1) {
            MutableList(pattern.length + 1) { false }
        }

        memo[0][0] = true
        // handle zero length pattern matches (a*, a*b*c*, etc.)
        for (p in pattern.indices) {
            if (pattern[p] == '*') {
                memo[0][p + 1] = memo[0][p - 1]
            }
        }

        for (i in 1 until memo.size) {
            for (j in 1 until memo[0].size) {
                if (text[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    memo[i][j] = memo[i - 1][j - 1]
                } else if (pattern[j - 1] == '*') {
                    memo[i][j] = memo[i][j - 2]
                    if (pattern[j - 2] == '.' || pattern[j - 2 ] == text[i - 1]) {
                        memo[i][j] = memo[i - 1][j] || memo[i][j]
                    }
                } else {
                    memo[i][j] = false
                }
            }
        }

        if (debug) memo.print()

        return memo[text.length][pattern.length]
    }

    private fun <T> List<List<T>>.print() {
        map { l ->
            println(l.map { it.toString().first() })
        }
    }
}


data class Test(val input: String, val pattern: String, val solution: Boolean)

@Suppress("SpellCheckingInspection")
fun main() {
    val tests = listOf(
            Test("aa", "a", false),
            Test("aa", "a*", true),
            Test("aa", ".*", true),
            Test("aab", "c*a*b", true),
            Test("mississippi", "mis*is*p*.", false),
            Test("aa", "aa", true),
            Test("aa", "aa*", true),
            Test("ab", ".*c", false),
            Test("asdsabc", ".*c", true),
            Test("aaa", "a*a", true),
            Test("", "", true),
            Test("a", "", false),
            Test("", "a*", true),
            Test("aaa", "ab*a*c*a", true),
            Test("xaabyc", "xa*b.c", true)
    )

    val s = Solution3(debug = true)

    tests.forEach {
        println("test: $it")
        val result = s.isMatch(it.input, it.pattern)
        println(result)
        if (result != it.solution) throw Exception("test case $it failed, result $result, expected ${it.solution}")
    }
}