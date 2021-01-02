package exercises.searchinsertposition

class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.lastIndex

        while (start <= end) {
            loops++
            val mid = ((end - start) / 2) + start
            when {
                nums[mid] > target -> end = mid - 1
                nums[mid] < target -> start = mid + 1
                else -> return mid
            }
        }
        return start
    }

    companion object {
        var loops = 0
    }
}

data class Test(val nums: List<Int>, val target: Int, val solution: Int)

fun main() {
    val tests = listOf(
            Test(nums = listOf(1,3,5,6), target = 5, solution = 2),
            Test(nums = listOf(1,3,5,6), target = 2, solution = 1),
            Test(nums = listOf(1), target = 0, solution = 0)
    )

    val s = Solution()

    tests.forEach { test ->
        println("test: $test")
        exercises.searchrotatedarray.Solution.loops = 0
        val result = s.searchInsert(test.nums.toIntArray(), test.target)
        println("$result loops:${Solution.loops}")
        if (result != test.solution) throw Exception("test case $test failed, result $result, expected ${test.solution}")
    }
}