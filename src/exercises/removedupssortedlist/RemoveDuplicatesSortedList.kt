package exercises.removedupssortedlist

/*
* https://leetcode.com/problems/remove-duplicates-from-sorted-array/
*/

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var uniqueCount = 1
        var lastVal = nums[0]
        var insertI = 1

        for (i in 1..nums.lastIndex) {
            if (nums[i] > lastVal) {
                nums[insertI] = nums[i]
                insertI++
                lastVal = nums[i]
                uniqueCount++
            }
        }

        return uniqueCount
    }
}

fun main() {
    val tests = listOf(
            listOf(1, 1, 2) to listOf(1, 2, 2),
            listOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4) to listOf(0, 1, 2, 3, 4, 2, 2, 3, 3, 4),
            emptyList<Int>() to emptyList(),
            listOf(1) to listOf(1)
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val input = it.first.toIntArray()
        val result = s.removeDuplicates(input)
        println("result:$result list:${input.toList()}")
        if (input.toList() != it.second) throw Exception("test $it failed, result ${input.toList()}, expected ${it.second}")
    }
}