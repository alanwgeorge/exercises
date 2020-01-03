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

fun main() {
    val nums = listOf(1,2,3,4,5,6,7,8,9).toIntArray()

    val s = SolutionTwoPass()

    println(s.twoSum(nums, 15).toList())
}