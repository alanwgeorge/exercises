package exercises.findfirstandlastindex

class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val firstFind = binarySearch(nums.toList(), target)

        if (firstFind == -1) return intArrayOf(-1, -1)

        var left = firstFind
        var right = firstFind

        while (left > 0 && nums[left - 1] == target) {
            loops++
            left--
        }
        while (right < nums.lastIndex && nums[right + 1] == target) {
            loops++
            right++
        }

        return intArrayOf(left, right)
    }

    fun binarySearch(source: List<Int>, target: Int): Int {
        var start = 0
        var end = source.lastIndex

        while (start <= end) {
            loops++
            val mid = ((end - start) / 2) + start
            when {
                source[mid] > target -> end = mid - 1
                source[mid] < target -> start = mid + 1
                else -> return mid
            }
        }
        return -1
    }

    companion object {
        var loops = 0
    }
}

data class Test(val nums: List<Int>, val target: Int, val solution: Pair<Int, Int>)

fun main() {
    val tests = listOf(
            Test(nums = listOf(5,7,7,8,8,10), target = 8, solution = 3 to 4),
            Test(nums = listOf(5,7,7,8,8,10), target = 6, solution = -1 to -1),
            Test(nums = listOf(7,7,8,8,10), target = 7, solution = 0 to 1),
            Test(nums = listOf(7,7,8,8,10,10,10), target = 10, solution = 4 to 6)
    )

    val s = Solution()

    tests.forEach { test ->
        println("test: $test")
        exercises.searchrotatedarray.Solution.loops = 0
        val result = s.searchRange(test.nums.toIntArray(), test.target).toList()
        println("$result loops:${Solution.loops}")
        if (result[0] != test.solution.first && result[1] != test.solution.second) throw Exception("test case $test failed, result $result, expected ${test.solution}")
    }
}