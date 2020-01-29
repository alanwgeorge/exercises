package exercises.`3sum`

import kotlin.math.ln

/*
* https://leetcode.com/problems/3sum/
*/

class Solution(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        loops += (ln(nums.size.toDouble()) * nums.size).toInt()
        val result = mutableSetOf<List<Int>>()
        for (i in 0..nums.lastIndex) {
            loops++
            for (j in (i + 1)..nums.lastIndex) {
                loops++
                val recip = (nums[i] + nums[j]) * -1
                if (debug) println("$i $j ${nums[i]} ${nums[j]} $recip")
                if (nums.sliceArray((j + 1)..nums.lastIndex).contains(recip)) {
                    result.add(listOf(recip, nums[i], nums[j]))
                    loops += nums.size
                }
            }
        }

        loops += result.size
        return result.toList()
    }
}

class Solution2(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        val recips = hashSetOf<Int>()

        for (i in 0..nums.lastIndex) {
            loops++
            recips.clear()
            for (j in (i + 1)..nums.lastIndex) {
                loops++
                val recip = -(nums[i] + nums[j])
                if (debug) println("$i $j ${nums[i]} ${nums[j]} $recip $recips")
                if (recips.contains(recip)) {
                    result.add(listOf(nums[i], nums[j], recip).sorted())
                } else {
                    recips.add(nums[j])
                }
            }
        }

        return result.toList()
    }
}

fun main() {
    val tests = listOf(
            listOf(-1, 0, 1, 2, -1, -4) to 2,
            listOf(0, -1, 2, -3, 1) to 2,
            listOf(1, -2, 1, 0, 5) to 1

    )

    val s = Solution2(debug = true)

    tests.forEach {
        println("test: $it")
        Solution2.loops = 0
        val result = s.threeSum(it.first.toIntArray())
        println("$result loops:${Solution2.loops}")
        if (result.size != it.second) throw Exception("test $it failed, got ${result.size} results, expected ${it.second}")
        result.forEachIndexed { index, list ->
            val sum = list.sum()
            if (sum != 0) throw Exception("result item $index, sum != 0, sum = $sum, list = $list")
        }
    }
}