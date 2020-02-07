package exercises.removeallnfromarray

/*
* https://leetcode.com/problems/remove-element/
*/

class Solution(private val debug: Boolean = false) {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var s = 0
        var e = nums.lastIndex
        while (s < e) {
            while (nums[s] != `val` && s < nums.lastIndex) s++
            while (nums[e] == `val` && e > 0) e--
            if (s >= e) break
            val tmp = nums[s]
            nums[s] = nums[e]
            nums[e] = tmp
            if (debug) println("swapping ${nums[s]} index $s with ${nums[e]} index $e: result ${nums.toList()}")
            s++
            e--
        }

        if (debug) println("$s $e")

        return when {
            e == 0 -> 0
            s > e -> s
            else -> s + 1
        }
    }
}

class Solution2(private val debug: Boolean = false) {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        when (nums.size) {
            0 -> return 0
            1 -> return nums.count { it != `val` }
        }

        var e = nums.lastIndex
        var result = 0
        while (nums[e] == `val` && e > 0) e--
        for (s in nums.indices) {
            if (s >= e) break
            if (debug) println("before result:$result nums[$s]:${nums[s]} nums[$e]:${nums[e]} list:${nums.toList()}")
            if (nums[s] == `val`) {
                swap(nums, s, e)
                while (nums[e] == `val` && e > 0) e--
            }

            result++
            if (debug) println("after result:$result nums[$s]:${nums[s]} nums[$e]:${nums[e]} list:${nums.toList()}")
        }

        return nums.count { it != `val` }
    }

    private fun swap(nums: IntArray, s: Int, e: Int) {
        if (debug) println("swapping $s with $e")
        val tmp = nums[s]
        nums[s] = nums[e]
        nums[e] = tmp
    }
}

class Solution3(private val debug: Boolean = false) {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var i = 0
        for (j in nums.indices) {
            if (nums[j] != `val`) {
                nums[i] = nums[j]
                i++
            }
        }

        return i
    }
}

data class Test(val nums: List<Int>, val remove: Int, val solution: List<Int>)

fun main() {
    val tests = listOf(
            Test(listOf(3, 2, 2, 3), 3, listOf(2, 2, 3, 3)),
            Test(listOf(0, 1, 2, 2, 3, 0, 4, 2), 2, listOf(0, 1, 4, 0, 3, 2, 2, 2)),
            Test(listOf(0, 1, 2, 2, 3, 0, 4, 2), 5, listOf(0, 1, 2, 2, 3, 0, 4, 2)),
            Test(listOf(5, 5, 5, 5, 5), 5, listOf(5, 5, 5, 5, 5)),
            Test(listOf(5, 0, 0, 0, 0), 5, listOf(0, 0, 0, 0, 5)),
            Test(listOf(0, 0, 0, 0, 5), 5, listOf(0, 0, 0, 0, 5)),
            Test(listOf(0, 0, 0, 5, 0), 5, listOf(0, 0, 0, 0, 5)),
            Test(listOf(2), 3, listOf(2))
    )

    val s = Solution3(debug = true)

    tests.forEach {
        println("test: $it")
        val input = it.nums.toIntArray()
        val result = s.removeElement(input, it.remove)
        println("result:$result ${input.toList()}")
        if (input.toList().take(result).sorted() != it.solution.take(result).sorted() || result != it.nums.count { n -> n != it.remove }) throw Exception("test $it failed, result ${input.toList()}, expected ${it.solution}")
    }
}