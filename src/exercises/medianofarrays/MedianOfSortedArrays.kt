package exercises.medianofarrays

/*
* https://leetcode.com/problems/median-of-two-sorted-arrays/
*/

class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val totalLength = nums1.size + nums2.size
        val medianPoint = totalLength / 2

        println("$medianPoint $totalLength")

        val merged = mutableListOf<Int>()

        var a = 0
        var b = 0
        while (a <= nums1.lastIndex && b <= nums2.lastIndex) {
            println("$a $b")
            if (nums1[a] < nums2[b]) {
                merged.add(nums1[a++])
            } else if (nums2[b] < nums1[a]) {
                merged.add(nums2[b++])
            } else {
                merged.add(nums1[a++])
                merged.add(nums2[b++])
            }
        }

        while (a <= nums1.lastIndex) {
            merged.add(nums1[a++])
        }

        while (b <= nums2.lastIndex) {
            merged.add(nums2[b++])
        }

        println(merged)
        return when {
            merged.size == 1 -> merged.last().toDouble()
            merged.size == 2 -> (merged.first().toDouble() + merged.last().toDouble()) / 2
            merged.size % 2 != 0 -> merged[medianPoint].toDouble()
            else -> (merged[medianPoint].toDouble() + merged[medianPoint - 1].toDouble()) / 2
        }
    }
}

fun main() {
    val test1 = Triple(listOf(1,3).toIntArray(), listOf(2).toIntArray(), 2.0)
    val test2 = Triple(listOf(1,2).toIntArray(), listOf(3,4).toIntArray(), 2.5)
    val test3 = Triple(listOf(0,0).toIntArray(), listOf(0,0).toIntArray(), 0.0)
    val test4 = Triple(emptyArray<Int>().toIntArray(), listOf(1).toIntArray(), 1.0)
    val test5 = Triple( listOf(2).toIntArray(), emptyArray<Int>().toIntArray(), 2.0)
    val test6 = Triple(emptyArray<Int>().toIntArray(), listOf(2,3).toIntArray(), 2.5)
    val test7 = Triple(emptyArray<Int>().toIntArray(), listOf(1,2,3,4,5).toIntArray(), 3.0)

    val tests = listOf(test1, test2, test3, test4, test5, test6)
//    val tests = listOf(test7)

    val s = Solution()

    tests.forEach {
        val result = s.findMedianSortedArrays(it.first, it.second)
        if (result != it.third) throw Exception("tests failed got $result, expected ${it.third}")
        println(result)
    }
}