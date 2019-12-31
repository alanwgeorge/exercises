package exercises.journey

/*
* solution to monk visits Coderland exercise
* https://www.hackerearth.com/practice/algorithms/searching/ternary-search/practice-problems/algorithm/monk-visits-coderland-4/
* */

fun main() {
    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    repeat(numberOfTestCases) { _ ->
        val checkpoints = readLine()?.toInt() ?: throw Exception("checkpoint count not found")
        val fuelCost = readLine()?.split("""\s+""".toRegex(), limit = checkpoints)?.map { it.toLong() } ?: throw Exception("error parsing fuel costs")
        val checkpointFuelConsumption = readLine()?.split("""\s+""", limit = checkpoints)?.map { it.toLong() }?: throw Exception("error parsing next checkpoint fuel consumption")

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


