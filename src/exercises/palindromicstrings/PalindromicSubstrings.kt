package exercises.palindromicstrings

import kotlin.random.Random

/*
* https://leetcode.com/problems/longest-palindromic-substring/
*/

class Solution2 {
    companion object {
        var loops = 0
    }
    fun longestPalindrome(s: String): String {
        if (s.isEmpty()) return ""
        var start = 0
        var end = 0
        for (i in s.indices) {
            loops++
            val len1 = expandAroundCenter(s, i, i)
            val len2 = expandAroundCenter(s, i, i + 1)
            val len = Math.max(len1, len2)
            if (len > end - start) {
                start = i - (len - 1) / 2
                end = i + len / 2
            }
        }
        return s.substring(start, end + 1)
    }

    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var L = left
        var R = right
        while (L >= 0 && R < s.length && s[L] == s[R]) {
            loops++
            L--
            R++
        }
        return R - L - 1
    }
}

class Solution {
    companion object {
        var loops = 0
    }
    data class CountPosition(val positions: List<Int>)

    fun longestPalindrome(s: String): String {
        if (s.isEmpty()) return ""
        val charCountPos = mutableMapOf<Char, CountPosition>()
        var lastFound = ""


        s.forEachIndexed { index, c ->
            loops++
            charCountPos[c]?.let {
                charCountPos[c] = it.copy(positions = it.positions + index)
            } ?: run {
                charCountPos[c] = CountPosition(listOf(index))
            }
        }

        charCountPos.forEach escape@{
            if (it.value.positions.size > 1) {
                val locationPairs = it.value.positions.uniquePermutations()
//                println("${it.key} $locationPairs")
                locationPairs.forEach { locs ->
                    loops++
                    val candidate = s.subSequence(locs.first..locs.second)
                    if (candidate.isPalindrome() && candidate.length > lastFound.length) {
                        lastFound = candidate.toString()
                        return@escape
                    }
                }
            }
        }

        if (lastFound.isEmpty()) lastFound = s.first().toString()

        return lastFound
    }

    fun CharSequence.isPalindrome(): Boolean {
        var low = 0
        var hi = lastIndex

        while (low < hi) {
            loops++
            if (get(low) != get(hi)) return false
            low++
            hi--
        }

        return true
    }

    fun List<Int>.uniquePermutations(): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        val foo = 1 - 2

        for (i in indices) {
            for (j in lastIndex downTo (i + 1)) {
                loops++
                result.add(get(i) to get(j))
            }
        }

        return result.sortedByDescending { it.second - it.first }
    }
}

data class Test(val arg: String, val solution: String)
fun main() {
//    val charPool = ('a'..'z').toList()
//    fun randomString() = (1..10000)
//            .map { i -> Random.nextInt(0, charPool.size) }
//            .map(charPool::get)
//            .joinToString("");
//
//    val s = Solution2()
//    repeat(10) {
//        val input = randomString()
//        val result = s.longestPalindrome(input)
//        println("${Solution2.loops} $result $input")
//        Solution2.loops = 0
//    }

    val tests = listOf(
            Test("babad", "bab"),
            Test("cbbd", "bb"),
            Test("djdjqwertyuioppoiuytrewqaaa", "qwertyuioppoiuytrewq"),
            Test("1234567890", "1"),
            Test("a", "a"),
            Test("ac", "a"),
            Test("babadada", "adada"),
            Test("abacdfgdcaba", "aba"),
            Test("12abba34", "abba"),
            Test("aaaabaaa", "aaabaaa")
    )

    val s = Solution()

    tests.forEach {
        val result = s.longestPalindrome(it.arg)
        println("$result ${Solution.loops} ${it.arg.length}")
        Solution.loops = 0
        if (result != it.solution) throw Exception("test failed $it expected ${it.solution} actual $result")
    }
}