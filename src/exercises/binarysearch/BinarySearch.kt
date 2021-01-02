package exercises.binarysearch

import exercises.bubbleSort
import exercises.readLineInput
import exercises.readLine
import exercises.searchBinary
import java.io.File

/*
* https://www.hackerearth.com/practice/algorithms/searching/binary-search/tutorial/
* */

fun main() {
    readLineInput = File("src/exercises/binarysearch/large-test1.txt").inputStream()

    val inputSize = readLine()?.toInt() ?: throw Exception("error parsing input size")
    val input = readLine()?.split(" ", limit = inputSize)?.map { it.toInt() }?.toMutableList() ?: throw Exception("error parsing input line")
    val numQueries = readLine()?.toInt() ?: throw Exception("error parsing number of queries")

    input.bubbleSort()

    repeat(numQueries) {
        val value = readLine()?.toInt() ?: throw Exception("error parsing input $it")
        println(input.searchBinary(value))
    }
}