package exercises

fun main() {
    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    for (test in 1..numberOfTestCases) {
        val checkpoints = readLine()?.toInt() ?: throw Exception("checkpoint count not found")
        val fuelCost = readLine()?.split(" ", limit = checkpoints)?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing fuel costs")
        val checkpointFuelConsumption = readLine()?.split(" ", limit = checkpoints)?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing next checkpoint fuel consuption")

        println(calculateJourneyMinCost(fuelCost, checkpointFuelConsumption))
    }
}

fun calculateJourneyMinCost(fuelCost: Array<Long>, checkpointFuelConsumption: Array<Long> ): Long {
    val lastcheckpoint = fuelCost.size - 1
    var fuelInCar = 0L
    var totalCost = 0L

    for (curCheckpoint in 0..lastcheckpoint) {
//        println("enter curCheckpoint:$curCheckpoint fuelInCar:$fuelInCar totalCost:$totalCost")
        //consume fuel
        if (curCheckpoint != 0) {
            fuelInCar = fuelInCar - checkpointFuelConsumption[curCheckpoint - 1]
            if (fuelInCar < 0L) throw Exception("you ran out of fuel")
        }

        if (curCheckpoint != lastcheckpoint && fuelCost[curCheckpoint + 1] > fuelCost[curCheckpoint]) {
            val litersNeeded = checkpointFuelConsumption[curCheckpoint] + checkpointFuelConsumption[curCheckpoint + 1]
            val cost = fuelCost[curCheckpoint] * litersNeeded
            fuelInCar += litersNeeded
            totalCost += cost
        }

        if(fuelInCar < checkpointFuelConsumption[curCheckpoint]) {
            val litersNeeded = checkpointFuelConsumption[curCheckpoint]
            val cost = fuelCost[curCheckpoint] * litersNeeded
            fuelInCar = fuelInCar + litersNeeded
            totalCost = totalCost + cost
        }
    }

    return totalCost
}
