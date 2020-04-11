package exercises.searchrotatedlist

/*
* https://leetcode.com/problems/search-in-rotated-sorted-array/
*/

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var result = -1



        return result
    }
}

data class Test(val nums: List<Int>, val target: Int, val solution: Int)

fun main() {
    val tests = listOf(
            Test(listOf(4, 5, 6, 7, 0, 1, 2), 0, 4)
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.search(it.nums.toIntArray(), it.target)
        println("result $result")
        if (it.solution != result) throw Exception("test $it failed, result $result, expected ${it.solution}")
    }
}