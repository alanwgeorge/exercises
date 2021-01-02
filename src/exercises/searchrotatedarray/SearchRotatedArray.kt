package exercises.searchrotatedarray

infix fun <P1, P2, R> P1.pipe2(t: (P1, P2) -> R): (P2) -> R = { p2 -> t(this, p2) }
infix fun <P1, P2, P3, R> P1.pipe3(t: (P1, P2, P3) -> R): (P2, P3) -> R = { p2, p3 -> t(this, p2, p3) }

class Solution {
    fun search(nums: IntArray, target: Int): Int {

        val derefIndex = nums.size pipe2
                (findPivotIndex(nums) pipe3 ::translatePivot)

        return binarySearch(nums.toList(), target, derefIndex)
    }

    fun findPivotIndex(nums: IntArray): Int {
        for (i in nums.indices) {
            loops++
            if (i == nums.lastIndex) return 0
            if (nums[i] > nums[i + 1]) return i + 1
        }

        throw Exception("input not sorted")
    }

    fun translatePivot(pivot: Int, length: Int, index: Int): Int {
        if (index >= length) throw Exception("index out of bounds: index:$index length:$length")
        if (pivot == 0) return index
        if (index == 0) return pivot
        return if ((index + pivot) < length) {
            index + pivot
        } else {
            (pivot + index) - length
        }
    }

    fun binarySearch(source: List<Int>, target: Int, derefFuc: (Int) -> Int): Int {
        var start = 0
        var end = source.lastIndex

        while (start <= end) {
            loops++
            if (loops > (source.size * 2)) throw Exception("endless loop detected")
            val mid = ((end - start) / 2) + start
            val derefValue = source[derefFuc(mid)]
//            println("start:$start mid:$mid end:$end derefMid:${derefFuc(mid)} derefValue:$derefValue target:$target")
            if (derefValue == target) return derefFuc(mid)
            if (derefValue > target) end = mid - 1
            if (derefValue < target) start = mid + 1
        }
        return -1
    }

    companion object {
        var loops = 0
    }
}

data class Test(val nums: List<Int>, val target: Int, val solution: Int)

fun main() {
    val tests = listOf(
            Test(nums = listOf(4, 5, 6, 7, 0, 1, 2), target = 0, solution = 4),
            Test(nums = listOf(4, 5, 6, 7, 0, 1, 2), target = 3, solution = -1),
            Test(nums = listOf(1), target = 0, solution = -1)
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