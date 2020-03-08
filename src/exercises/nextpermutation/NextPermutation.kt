@file:Suppress("unused")

package exercises.nextpermutation

/*
* https://leetcode.com/problems/next-permutation/
*/

class Solution(private val debug: Boolean = false) {
    fun nextPermutation(nums: IntArray) {
        if (nums.isEmpty()) return

        var y = nums.lastIndex
        var x = y - 1

        var largerSwap: Pair<Int, Int> = 0 to 0

        while (x >= 0) {
            if (nums[y] > nums[x]) {
                nums.swap(x ,y)
                largerSwap = x to y
                x = -1
            } else {
                y--
                x--
            }
        }

        if (debug) println("largerSwap: $largerSwap")

        var swapCount: Int
        do {
            swapCount = 0
            for (i in nums.lastIndex downTo (largerSwap.second + 1)) {
                if (nums[i] < nums[i - 1]) {
                    nums.swap(i, i - 1)
                    swapCount++
                }
            }
        } while (swapCount > 0)
    }

    private fun IntArray.swap(x: Int, y: Int) {
        if (x > lastIndex || y > lastIndex || x == y) return
        val temp = get(x)
        set(x, get(y))
        set(y, temp)
    }
}

class Solution2(private val debug: Boolean = false) {
    fun nextPermutation(nums: IntArray) {
        if (nums.isEmpty()) return

        var gettingSmallerIndx = -1
        for (x in nums.lastIndex downTo 1) {
            if (nums[x] > nums[x - 1]) {
                gettingSmallerIndx = x - 1
                break
            }
        }

        if (gettingSmallerIndx >= 0) {
            var diff = Int.MAX_VALUE
            var nextLargestIndx = -1

            for (x in (gettingSmallerIndx + 1)..nums.lastIndex) {
                val currDiff = nums[x] - nums[gettingSmallerIndx]
                if (currDiff in 1 until diff) {
                    diff = currDiff
                    nextLargestIndx = x
                }
            }

            if (debug) println("gettingSmallerIndx:$gettingSmallerIndx nextLargestIndx:$nextLargestIndx")

            if (nextLargestIndx > 0) nums.swap(gettingSmallerIndx, nextLargestIndx)
        }

        var swapCount: Int
        do {
            swapCount = 0
            for (i in (gettingSmallerIndx + 1) until nums.lastIndex) {
                if (nums[i] > nums[i + 1]) {
                    nums.swap(i, i + 1)
                    swapCount++
                }
            }
        } while (swapCount > 0)
    }

    private fun IntArray.swap(x: Int, y: Int) {
        if (x > lastIndex || y > lastIndex || x == y) return
        val temp = get(x)
        set(x, get(y))
        set(y, temp)
    }
}

fun main() {
    val tests = listOf(
            listOf(3,5,7,6,2) to listOf(3,6,2,5,7),
            listOf(1,2,3) to listOf(1,3,2),
            listOf(3,2,1) to listOf(1,2,3),
            listOf(1,1,5) to listOf(1,5,1),
            listOf(1,2) to listOf(2,1),
            listOf(1,3,2) to listOf(2,1,3)
    )

    val s = Solution2(debug = true)

    tests.forEach {
        println("test: $it")
        val result = it.first.toIntArray()
        s.nextPermutation(result)
        println("result: ${result.toList()}")
        if (result.toList() != it.second) throw Exception("test $it failed, result ${result.toList()}, expected ${it.second}")
    }
}