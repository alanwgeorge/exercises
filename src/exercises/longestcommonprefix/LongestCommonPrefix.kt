package exercises.longestcommonprefix

/*
* https://leetcode.com/problems/longest-common-prefix/
*/

class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        var prefix = strs.getOrNull(0) ?: return ""

        strs.drop(1).forEach { str ->
            val newPrefix = mutableListOf<Char>()
            var i = 0
            while (i < str.length && i < prefix.length) {
                if (str[i] == prefix[i]) newPrefix.add(str[i]) else break
                i++
            }
            prefix = newPrefix.joinToString("")
        }

        return prefix
    }
}

fun main() {
    val tests = listOf(
            listOf("flower","flow","flight") to "fl",
            listOf("dog","racecar","car") to "",
            listOf("aca","cba") to ""
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.longestCommonPrefix(it.first.toTypedArray())
        println(result)
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}