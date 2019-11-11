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

            println(calculateJourneyMinCost(fuelCost, checkpointFuelConsumption))
        }
    }

    println("measure = ${NumberFormat.getInstance().format(measure)}")
}

fun calculateJourneyMinCost(fuelCost: Array<Long>, checkpointFuelConsumption: Array<Long> ): Long {
    var totalCost = 0L
    var minCost = Long.MAX_VALUE

    for (curCheckpoint in 0..(fuelCost.size - 1)) {
        if (fuelCost[curCheckpoint] < minCost) {
            minCost = fuelCost[curCheckpoint]
        }

        totalCost += minCost * checkpointFuelConsumption[curCheckpoint]
    }

    return totalCost
}