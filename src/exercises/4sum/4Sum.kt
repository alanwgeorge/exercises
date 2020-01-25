@file:Suppress("PackageName", "unused", "LocalVariableName")

package exercises.`4sum`

import kotlin.math.ln

/*
* https://leetcode.com/problems/4sum/
*/

class Solution {
    companion object {
        var loops = 0
    }

    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()

        for (i in nums.indices) {
            for (j in (i + 1)..nums.lastIndex) {
                for (k in (j + 1)..nums.lastIndex) {
                    for (l in (k + 1)..nums.lastIndex) {
                        loops++
                        val sum = listOf(nums[i], nums[j], nums[k], nums[l])
                        if (sum.sum() == target) result.add(sum.sorted())
                    }
                }
            }
        }

        return result.toList()
    }
}

class Solution2 {
    companion object {
        var loops = 0
    }

    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val _nums = nums.sorted()
        loops += ln(nums.size.toDouble()).toInt()
        val result = mutableSetOf<List<Int>>()

        for (i in _nums.indices) {
            for (j in (i + 1).._nums.lastIndex) {
                var k = j + 1
                var l = +_nums.lastIndex
                while (l > k) {
                    loops++
                    val list = listOf(_nums[i], _nums[j], _nums[k], _nums[l])
                    val sum = list.sum()
                    when {
                        sum > target -> l--
                        sum < target -> k++
                        sum == target -> {
                            result.add(list)
                            l--
                            k++
                        }
                    }
                }
            }
        }

        return result.toList()
    }
}

data class Test(val nums: List<Int>, val target: Int, val solution: List<List<Int>>)

fun main() {
    val tests = listOf(
            Test(listOf(1, 0, -1, 0, -2, 2), 0, listOf(listOf(-1,  0, 0, 1), listOf(-2, -1, 1, 2), listOf(-2,  0, 0, 2))),
            Test(listOf(-3,-2,-1,0,0,1,2,3), 0, listOf(listOf(-3,-2,2,3), listOf(-3,-1,1,3), listOf(-3,0,0,3), listOf(-3,0,1,2), listOf(-2,-1,0,3), listOf(-2,-1,1,2), listOf(-2,0,0,2), listOf(-1,0,0,1))),
            Test(listOf(-500,-490,-471,-456,-422,-412,-406,-398,-381,-361,-341,-332,-292,-288,-272,-236,-235,-227,-207,-203,-185,-119,-59,-13,4,5,46,72,82,91,92,130,130,140,145,159,187,207,211,226,239,260,262,282,290,352,377,378,386,405,409,430,445,478,481,498), -3213, listOf())
    )

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        val result = s.fourSum(it.nums.toIntArray(), it.target)
        println("result:$result loops:${Solution2.loops}")
        if (result.size != it.solution.size) throw Exception("test $it failed, incorrect number of results, found ${result.size}, expected ${it.solution.size}")
        result.forEach { r ->
            if (r.sum() != it.target) throw Exception("test $it failed, result $r not equal target ${it.target}")
        }
    }

}


//[-3, -2, 2, 3], [-3, -1, 1, 3], [-3, 0, 0, 3], [-3, 0, 1, 2], [-2, -1, 0, 3], [-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]
//[-3, -2, 2, 3], [-3, -1, 1, 3], [-3, 0, 0, 3], [-3, 0, 1, 2], [-2, -1, 0, 3], [-2, 0, 0, 2], [-1, 0, 0, 1]