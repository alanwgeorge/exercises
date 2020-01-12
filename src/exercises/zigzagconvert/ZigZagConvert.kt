package exercises.zigzagconvert

/*
* https://leetcode.com/problems/zigzag-conversion/
*/

class Solution {
    fun convert(s: String, numRows: Int): String {
        val interval = numRows + numRows - 2
        var level = 0
        var lastPos = 0
        val result = MutableList(s.length) { '0' }

        s.forEachIndexed escape@{ index, c ->
            calcPlace(index, numRows, s.lastIndex)
        }

        println(result)
        return ""
    }

    // 0 based calculation
    fun calcPlace(origPosition: Int, numRows: Int, lastIndex: Int): Int {
//        if (origPosition == 0) return 0
        val interval = numRows + numRows - 2
        val currentInterval = origPosition / interval
        val posInInterval = origPosition % interval
        val depth = if (posInInterval > (interval / 2)) interval - posInInterval else posInInterval
        var pos = (depth * interval) + currentInterval

        println("$origPosition $currentInterval $depth $pos")

        return pos
    }
}

data class Test(val args: Pair<String, Int>, val solution: String)
fun main() {
    val test1 = Test("PAYPALISHIRING" to 3, "PAHNAPLSIIGYIR")
    val test2 = Test("PAYPALISHIRING" to 4, "PINALSIGYAHRPI")
    val test3 = Test("ABCDEFGHIJKLMN" to 3, "AEIMBDFHJLNCGK")

    val tests = listOf(test3)

    val s = Solution()
    tests.forEach {
        val result = s.convert(it.args.first, it.args.second)
//        if (result != it.solution) throw Exception("test case $it failed, result $result, expected ${it.solution}")
    }
}