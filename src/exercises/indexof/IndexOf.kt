package exercises.indexof

/*
* https://leetcode.com/problems/implement-strstr/
*/

class Solution {
    fun strStr(haystack: String, needle: String): Int {
        if (haystack.isEmpty() && needle.isNotEmpty()) return -1
        if (haystack.isEmpty()) return 0
        if (needle.isEmpty()) return 0
        if (needle.length > haystack.length) return -1

        for (h in haystack.indices) {
            if (haystack[h] == needle[0]) {
                for (n in needle.indices) {
                    if (needle[n] != haystack.getOrNull(h + n)) break
                    if (n == needle.lastIndex) return h
                }
            }
        }

        return -1
    }
}

data class Test(val haystack: String, val needle: String, val solution: Int)

fun main() {
    val tests = listOf(
            Test("hello", "ll", 2),
            Test("aaaaa", "bba", -1),
            Test("aaaaa", "abb", -1),
            Test("hello", "hello", 0),
            Test("hello", "he", 0),
            Test("hello", "h", 0),
            Test("hello", "lo", 3),
            Test("hello", "o", 4),
            Test("", "o", -1),
            Test("h", "", 0),
            Test("mississippi","issip", 4)
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.strStr(it.haystack, it.needle)
        println(result)
        if (result != it.solution) throw Exception("test $it failed, result $result, expected ${it.solution}")
    }
}