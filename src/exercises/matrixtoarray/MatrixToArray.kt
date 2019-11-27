package exercises.matrixtoarray

import exercises.bubbleSort
import kotlin.math.absoluteValue

/*
* https://www.hackerearth.com/practice/algorithms/searching/binary-search/practice-problems/algorithm/monk-and-new-array/
* */

fun main() {
    val (n, m) = readLine()?.split(" ", limit = 2)?.map { it.toInt() }?.toTypedArray()?.take(2) ?: throw Exception("error parsing matrix size")

    val matirx = mutableListOf<List<Int>>()

    repeat(n) {
        val matrixLine = readLine()?.split(" ", limit = m)?.map { it.toInt() }?.toMutableList()?.bubbleSort() ?: throw Exception("error parsing matrix line $it")
        matirx.add(matrixLine)
    }

//    matirx.print()

    val diffs = mutableListOf<Int>()
    for (i in 0 until matirx.lastIndex) {
        val smallestDiff = smallestDiff(matirx[i], matirx[i + 1])
        diffs.add(smallestDiff)
//        println(smallestDiff)
    }

    println(diffs.min())
}

fun smallestDiff(one: List<Int>, two: List<Int>): Int {
    if (one.last() < two.first()) {
        return two.first() - one.last()
    }

    if (two.last() < one.first()) {
        return one.first() - two.last()
    }

    var smallestDiff = Int.MAX_VALUE
    var o = 0
    var t = 0
    while (o <= one.lastIndex && t <= two.lastIndex) {
        val diff = (one[o] - two[t]).absoluteValue
        if (diff < smallestDiff)
            smallestDiff = diff

//        println("one[$o]=${one[o]} two[$t]=${two[t]} diff=$diff")

        if (smallestDiff == 0) break

        if (one[o] < two[t]) {
            o++
        } else {
            t++
        }
    }

    return smallestDiff
}