package exercises.searchrotatedarray


class Solution {
    fun search(nums: IntArray, target: Int): Int {
        val pivot = findPivotIndex(nums)

        return translatePivot(1, pivot, nums.size)
    }

    fun findPivotIndex(nums: IntArray): Int {
        for (i in nums.indices) {
            loops++
            if (i == nums.lastIndex) return 0
            if (nums[i] > nums[i + 1]) return i + 1
        }

        throw Exception("input not sorted")
    }

    fun translatePivot(index: Int, pivot: Int, length: Int): Int {
        if (index >= length) throw Exception("index out of bounds: index:$index length:$length")
        if (pivot == 0) return index
        if ((index + pivot) < length) {
            return index + pivot
        } else {
            return (pivot + index) - length
        }
    }

    companion object {
        var loops = 0
    }
}

data class Test(val nums: List<Int>, val target: Int, val solution: Int)

fun main() {
    val tests = listOf(
            Test(nums = listOf(4, 5, 6, 7, 0, 1, 2), target = 0, solution = 4),
            Test(nums = listOf(4, 5, 6, 7, 0, 1, 2), target = 3, solution = -1)
    )

    val s = Solution()

    tests.forEach { test ->
        println("test: $test")
        Solution.loops = 0
        val result = s.search(test.nums.toIntArray(), test.target)
        println("$result loops:${Solution.loops}")
        if (result != test.solution) throw Exception("test case $test failed, result $result, expected ${test.solution}")
    }
}