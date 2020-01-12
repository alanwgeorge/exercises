package exercises.zigzagconvert

import kotlin.math.min

/*
* https://leetcode.com/problems/zigzag-conversion/
*/

class Solution {
    companion object {
        var loops = 0
    }

    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s
        val result = MutableList(s.length) {
            loops++
            null as Char?
        }

        s.forEachIndexed { index, c ->
            loops++
            val newPos = calcPlace(index,numRows, s.lastIndex, c)
            result[newPos] = c
        }

        loops += result.size
        return result.joinToString("")
    }

    fun convert2(s: String, numRows: Int): String =
         buildZigZag(s, numRows).flatMap {
             loops++
             loops += it.size
            it.filterNotNull()
         }.joinToString("").also {
             loops += it.length
         }

    fun convert3(s: String, numRows: Int): String {
        if (numRows == 1) return s
        val ret = StringBuilder()
        val n = s.length
        val cycleLen = 2 * numRows - 2
//        println("cycleLen = $cycleLen")
        for (i in 0 until numRows) {
            loops++
            var j = 0
            while (j + i < n) {
                loops++
                ret.append(s[j + i])
//                println("j$j + i$i = ${j + i}")
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ret.append(s[j + cycleLen - i])
//                    println("j$j + cycleLen$cycleLen - i$i = ${j + cycleLen - i}")
                }
                j += cycleLen
            }
        }
        return ret.toString()
    }

    // 0 based calculation
    fun calcPlace(origPosition: Int, numRows: Int, lastIndex: Int, @Suppress("UNUSED_PARAMETER") c: Char): Int {
        val intervalSize = numRows + numRows - 2
        val currentInterval = origPosition / intervalSize
        val intervalCount = (lastIndex / intervalSize) + 1
        val posInInterval = origPosition % intervalSize
        val depth = if (posInInterval > (intervalSize / 2)) intervalSize - posInInterval else posInInterval
        val missingFromLastInterval = (intervalSize * intervalCount) - (lastIndex + 1)

        val maxValuesInRow = MutableList(numRows) { row ->
            loops++
            if (row == 0 || row == numRows - 1) {
                intervalCount
            } else {
                intervalCount * 2
            }
        }

        for (i in 1..missingFromLastInterval) {
            loops++
            val row = if (i > (intervalSize / 2)) intervalSize - i else i
            maxValuesInRow[row]--
        }

        val pos = if (depth == 0) {
            currentInterval
        } else {
            var collector = currentInterval

            if ((depth + 1) != numRows) {
                collector += currentInterval
            }

            for (i in 0 until depth) {
                loops++
                collector += maxValuesInRow[i]
            }

            if (posInInterval > (intervalSize / 2)) collector + 1 else collector
        }

//        println("$c $origPosition $currentInterval $depth $pos")

        return pos
    }

    fun buildZigZag(s: String, numRows: Int): List<List<Char?>> {
        if (numRows == 1) return listOf(s.toList())
        val intervalSize = numRows + numRows - 2
        val intervalCount = (s.length / intervalSize) + 1
        val missingFromLastInterval = (intervalSize * intervalCount) - s.length
        val maxX = ((intervalSize / 2) * intervalCount) - min(missingFromLastInterval, (intervalSize - numRows))

        val zz = mutableListOf<MutableList<Char?>>()

        repeat(numRows) {
            loops++
            zz.add(MutableList(maxX) { null as Char? })
        }

        var currentX = 0
        var currentRow = 0
        var goingDown = true

        for (c in s) {
            loops++
            zz[currentRow][currentX] = c

            if (goingDown) {
                if (currentRow < zz.lastIndex) {
                    currentRow++
                } else {
                    goingDown = false
                    currentRow--
                    currentX++
                }
            } else {
                if (currentRow > 0) {
                    currentRow--
                    currentX++
                } else {
                    currentRow = 1
                    goingDown = true
                }
            }
        }

        return zz
    }
}

data class Test(val args: Pair<String, Int>, val solution: String)
fun main() {
    val test1 = Test("PAYPALISHIRING" to 3, "PAHNAPLSIIGYIR")
    val test2 = Test("PAYPALISHIRING" to 4, "PINALSIGYAHRPI")
    val test3 = Test("ABCDEFGHIJKLMN" to 3, "AEIMBDFHJLNCGK")
    val test4 = Test("ABCDEFGHIJKLMNOPQRSTUVWZYZABC" to 6, "AKUBJLTVCIMSWCDHNRZBEGOQYAFPZ")

    val tests = listOf(test1, test2, test3, test4)

    val s = Solution()
    tests.forEach {
        println("test $it")
        s.buildZigZag(it.args.first, it.args.second).forEach { line ->
            line.map {
                it ?: ' '
            }.joinToString("").run {
                println(this)
            }
        }

        Solution.loops = 0
        var result = s.convert(it.args.first, it.args.second)
        if (result != it.solution) throw Exception("test case $it failed, result $result, expected ${it.solution}")
        println("convert() loops: ${Solution.loops}")

        Solution.loops = 0
        result = s.convert2(it.args.first, it.args.second)
        if (result != it.solution) throw Exception("test case $it failed, result $result, expected ${it.solution}")
        println("convert2() loops: ${Solution.loops}")

        Solution.loops = 0
        result = s.convert3(it.args.first, it.args.second)
        if (result != it.solution) throw Exception("test case $it failed, result $result, expected ${it.solution}")
        println("convert4() loops: ${Solution.loops}")
    }
}