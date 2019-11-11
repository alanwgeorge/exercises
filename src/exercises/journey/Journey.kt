package exercises.journey

/*
* solution to monk visits coderland exercise
* https://www.hackerearth.com/practice/algorithms/searching/ternary-search/practice-problems/algorithm/monk-visits-coderland-4/
* */

fun main() {
    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    repeat(numberOfTestCases) {
        val checkpoints = readLine()?.toInt() ?: throw Exception("checkpoint count not found")
        val fuelCost = readLine()?.split(" ", limit = checkpoints)?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing fuel costs")
        val checkpointFuelConsumption = readLine()?.split(" ", limit = checkpoints)?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing next checkpoint fuel consumption")

        println(calculateJourneyMinCost(fuelCost, checkpointFuelConsumption))
    }
}

fun calculateJourneyMinCost(fuelCost: Array<Long>, checkpointFuelConsumption: Array<Long> ): Long {
    val lastCheckpoint = fuelCost.size - 1
    var fuelInTank = 0L
    var totalCost = 0L

    for (curCheckpoint in 0..lastCheckpoint) {
        //consume fuel
        if (curCheckpoint != 0) {
            fuelInTank -= checkpointFuelConsumption[curCheckpoint - 1]
            if (fuelInTank < 0L) throw Exception("you ran out of fuel")
        }

        if (fuelInTank > 0) continue // if we have any fuel, we should be able to get to the next checkpoint

        val curFuelCost = fuelCost[curCheckpoint]

        var howMuchToBuy = checkpointFuelConsumption[curCheckpoint] // we will always need buy enough fuel to get to the next checkpoint

        // here we find the next cheapest fuel checkpoint and buy enough fuel to get us there
        // if we are currently on the cheapest, we will buy enough fuel to make it to the end
        for(futureCheckpoint in (curCheckpoint + 1)..lastCheckpoint) {
            if (fuelCost[futureCheckpoint] > curFuelCost) {
                howMuchToBuy += checkpointFuelConsumption[futureCheckpoint]
            } else {
                break
            }
        }

        fuelInTank = howMuchToBuy
        totalCost += howMuchToBuy * curFuelCost
    }

    return totalCost
}
