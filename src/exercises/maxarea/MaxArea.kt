package exercises.maxarea

import java.util.*
import kotlin.math.min

/*
* https://leetcode.com/problems/container-with-most-water/
*/

class Solution(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }
    fun maxArea(height: IntArray): Int {
        var max = 0

        for (i in height.indices) {
            for (j in height.lastIndex downTo  i + 1) {
                loops++
                val area = ((j + 1) - (i + 1)) * min(height[i], height[j])
                if (area > max) max = area
                if (debug) println("$area ${i + 1} ${j + 1} ${height[i]} ${height[j]}")
            }
        }

        return max
    }
}

class Solution2(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun maxArea(height: IntArray): Int {
        var max = 0

        var start = 0
        var end = height.lastIndex

        while (start < end) {
            if (debug) println("$start $end $max")
            loops++
            val area = ((end + 1) - (start + 1)) * min(height[start], height[end])
            if (area > max) max = area

            if (height[start] > height[end]) {
                end--
            } else {
                start++
            }
        }

        return max
    }
}

fun main() {
    val tests = listOf(
            intArrayOf(1,8,6,2,5,4,8,3,7) to 49,
            intArrayOf(2,3,4,5,18,17,6) to 17
    )

    val s = Solution2(debug = true)

    tests.forEach {
        println("test: ${it.first.toList()} ${it.second}")
        Solution2.loops = 0
        val result = s.maxArea(it.first)
        println("$result loops:${Solution2.loops}")
        if (result != it.second) throw Exception("test case $it failed, result $result, expected ${it.second}")
    }
}