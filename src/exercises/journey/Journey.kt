package exercises.journey

import java.text.NumberFormat
import kotlin.system.measureNanoTime

/*
* solution to monk visits coderland exercise
* https://www.hackerearth.com/practice/algorithms/searching/ternary-search/practice-problems/algorithm/monk-visits-coderland-4/
* */

fun main() {
    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    val measure = measureNanoTime {
        repeat(numberOfTestCases) {
            val checkpoints = readLine()?.toInt() ?: throw Exception("checkpoint count not found")
            val fuelCost = readLine()?.split(" ", limit = checkpoints)?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing fuel costs")
            val checkpointFuelConsumption = readLine()?.split(" ", limit = checkpoints)?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing next checkpoint fuel consumption")

            var totalCost = 0L
            var minCost = Long.MAX_VALUE
            for (curCheckpoint in fuelCost.indices) {
                if (fuelCost[curCheckpoint] < minCost) {
                    minCost = fuelCost[curCheckpoint]
                }

                totalCost += minCost * checkpointFuelConsumption[curCheckpoint]
            }

            println(totalCost)
        }
    }
    println("measure = ${NumberFormat.getInstance().format(measure)}")
}

