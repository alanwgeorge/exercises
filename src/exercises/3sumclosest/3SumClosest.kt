@file:Suppress("LocalVariableName", "PackageName", "unused", "DuplicatedCode")

package exercises.`3sumclosest`

import kotlin.math.abs
import kotlin.math.ln

/*
* https://leetcode.com/problems/3sum-closest/
*/

class Solution(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun threeSumClosest(nums: IntArray, target: Int): Int {
        var result = 0
        var minDistance = Int.MAX_VALUE

        if (debug) println("target: $target")

        for (i in nums.indices) {
            for (j in (i + 1)..nums.lastIndex) {
                for (k in (j + 1)..nums.lastIndex) {
                    loops++
                    val sum = nums[i] + nums[j] + nums[k]
                    val targetDistance = abs(sum - target)
                    if (debug) println(("${nums[i]} ${nums[j]} ${nums[k]} $sum $targetDistance"))
                    if (targetDistance < minDistance) {
                        minDistance = targetDistance
                        result = sum
                    }
                }
            }
        }

        return result
    }
}

class Solution2(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun threeSumClosest(nums: IntArray, target: Int): Int {
        val _nums = nums.sorted()
        loops += ln(nums.size.toDouble()).toInt()
        var result = 0
        var minDifference = Int.MAX_VALUE
        if (debug) println("target: $target nums: $_nums")

        for (i in _nums.indices) {
            for (j in (i + 1).._nums.lastIndex) {
                loops++
                val sum = _nums[i] + _nums[j]
                val recip = -(sum) + target
                val subList = _nums.filterIndexed { index, _ ->
                    loops++
                    index != i && index !=j
                }
                val closestRecip = findClosest(subList, recip)
                val difference = abs(recip - closestRecip)

                if (difference < minDifference) {
                    minDifference = difference
                    result = closestRecip + sum
                }
//                if (debug) println("i:$i j:$j ${_nums[i]} ${_nums[j]} sum:$sum recip:$recip closeestRecip:$closestRecip minDif:$minDifference diff:$difference result:$result")
            }
        }

        return result
    }



    private fun findClosest(nums: List<Int>, value: Int): Int {
        var start = 0
        var end = nums.lastIndex
        var mid: Int
        var closestDistance = Int.MAX_VALUE
        var result = 0

//        if (debug) println("value:$value nums:$nums")

        while (start <= end) {
            loops++
            mid = (start + end) / 2
            val distance = abs(nums[mid] - value)
            if (distance < closestDistance) {
                closestDistance = distance
                result = nums[mid]
            }
//            if (debug) println("$start $mid $end ${nums[mid]} $distance $result ${nums.subList(start, end)}")
            when {
                nums[mid] > value -> end = mid - 1
                nums[mid] < value -> start = mid + 1
                else -> return nums[mid]
            }
        }

        return result
    }
}

class Solution3(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun threeSumClosest(nums: IntArray, target: Int): Int {
        val _nums = nums.sorted()
        loops += ln(nums.size.toDouble()).toInt()
        var result = 0
        var minDistance = Int.MAX_VALUE

        for (i in 0..(nums.lastIndex - 2)) {
            loops++
            var j = i + 1
            var k = nums.lastIndex

            while (k > j) {
                loops++
                val sum = _nums[i] + _nums[j] + _nums[k]
                val distance = abs(sum - target)
                if (distance < minDistance) {
                    minDistance = distance
                    result = sum
                }
                when {
                    sum > target -> k--
                    sum < target -> j++
                    else -> return result
                }
            }
        }

        return result
    }
}
data class Test(val nums: List<Int>, val target: Int, val solution: Int)

fun main() {
    val tests = listOf(
            Test(listOf(-1, 2, 1, -4), 1, 2),
            Test(listOf(6,-18,-20,-7,-15,9,18,10,1,-20,-17,-19,-3,-5,-19,10,6,-11,1,-17,-15,6,17,-18,-3,16,19,-20,-3,-17,-15,-3,12,1,-9,4,1,12,-2,14,4,-4,19,-20,6,0,-19,18,14,1,-15,-5,14,12,-4,0,-10,6,6,-6,20,-8,-6,5,0,3,10,7,-2,17,20,12,19,-13,-1,10,-1,14,0,7,-3,10,14,14,11,0,-4,-15,-8,3,2,-5,9,10,16,-4,-3,-9,-8,-14,10,6,2,-12,-7,-16,-6,10), -52, -52),
            Test(listOf(0,1,2), 0, 3),
            Test(listOf(1,1,1,0), 100, 3),
            Test(listOf(1,1,1,0), -100, 2),
            Test(listOf(-12,-67,-44,27,59,-91,-51,39,21,9,-28,-63,-53,-78,-30,75,-69,-74,-9,-5,30,34,-46,68,33,30,32,73,-14,-32,4,-52,13,19,-93,-23,53,69,-29,32,79,-56,87,-28,-12,11,32,56,88,34,32,68,-14,49,-5,-72,8,-27,90,28,-47,-84,0,-90,-47,-33,81,29,70,-3,-25,91,36,-61,4,-93,60,-96,-48,14,-42,-40,-81,16,-81,-57,27,-72,-31,88,-54,-19,-22,-7,67,-31,-35,97,-74,32,98,25,3,54,97,2,54,97,-3,79,-92,44,-80,-8,-85,73,-97,-39,28,66,68,-6,57,-30,-73,-96,16,-57,-11,-15,72,-43,19,100,42,3,0,-63,-45,83,-100,-14,15,79,-69,88,14,-41,45,12,38,-95,24,61,83,19,-53,65,71,-60,-2,-46,77,16,-25,41,43,68,-59,-2,4,91,21,-92,30,-98,31,15,28,96,94,-84,3,-70,-46,-71,-59,-75,-14,39,-84,-42,-12,-89,-89,60,18,50,61,-85,-57,-57,-14,97,-64,38,-3,95,70,-22,-66,5,92,-6,41,24,78), -19, -19)
    )
    val s = Solution3(debug = false)

    tests.forEach {
        println("test: $it")
        Solution3.loops = 0
        val result = s.threeSumClosest(it.nums.toIntArray(), it.target)
        println("$result loops: ${Solution3.loops} input size: ${it.nums.size}")
        if (result != it.solution) throw Exception("test $it failed, result was $result, expected ${it.solution}")
    }
}