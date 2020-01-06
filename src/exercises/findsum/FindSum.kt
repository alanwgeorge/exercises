@file:Suppress("unused")

package exercises.findsum

/*
* https://leetcode.com/problems/two-sum/
*/

class SolutionBruteForce {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (i == j) continue
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        throw Exception("no solution found")
    }
}

class SolutionTwoPass {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val numMap = nums.mapIndexed { idx, value ->
            value to idx
        }.toMap()

        nums.forEachIndexed { idx, value ->
            if (numMap.containsKey(target - value) && numMap[target - value] != idx) {
                numMap[target - value]?.let {
                    return intArrayOf(idx, it)
                } ?: throw Exception("containsKey but gets null")
            }
        }

        throw Exception("no solution found")
    }
}

class SolutionSortedSinglePass {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var start = 0
        var end = nums.lastIndex
        val sorted = nums.mapIndexed { idx, value -> idx to value  }.sortedBy { it.second }

        while (start != end) {
            val sum = sorted[start].second + sorted[end].second
            when {
                sum == target -> return intArrayOf(sorted[start].first, sorted[end].first)
                sum > target -> end--
                sum < target -> start++
            }
        }

        throw Exception("no solution found")
    }
}

fun main() {
    val nums = listOf(1,2,3,4,5,6,7,8,9,10,11,25).toIntArray()

    val s = SolutionSortedSinglePass()

    println(s.twoSum(nums, 15).toList())
}