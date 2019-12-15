package exercises.cakeeating

import exercises.mergeSort


/*
* https://www.hackerearth.com/practice/algorithms/searching/binary-search/practice-problems/algorithm/monk-and-his-birthday-party/
*/

fun main() {
    data class Cake(val weight: Int, var count: Int)
    data class Guest(val capacity: Int, var cakesEaten: Int = 0, var totalWeightEaten: Int = 0)

    val numberOfTests = readLine()?.toInt() ?: throw Exception("error parsing number of tests")

    repeat(numberOfTests) { testRun ->
        val (numberOfCakeTypes, numberOfFriends) = readLine()?.split(" ", limit = 2)?.map { it.toInt() } ?: throw Exception("error parsing test $testRun")
        var guests = readLine()?.split(" ", limit = numberOfFriends)?.map { it.toInt() }?.sorted()?.map { Guest(it) } ?: throw Exception("error parsing friend capacities")
        val cakeWeight = readLine()?.split(" ", limit = numberOfCakeTypes)?.map { it.toInt() } ?: throw Exception("error parsing cake weights")
        val numberOfCakes = readLine()?.split(" ", limit = numberOfCakeTypes)?.map { it.toInt() } ?: throw Exception("error parsing numbers of cakes")

        val cakes = cakeWeight.zip(numberOfCakes) { weight, count ->
                Cake(weight, count)
        }.sortedBy {
            it.weight
        }.toMutableList()

        // filter out guests that can't eat the smallest cake
        guests = guests.filter {
            it.capacity < cakes.first().weight
        }

        val totalCakeCount = cakes.sumBy { it.count }
        val totalCakeWeight = cakes.sumBy { it.weight }
        var cakesEaten = 0
        var result = 0
        toobig@ while (cakesEaten != totalCakeCount) {
            result++
            for (g in guests.lastIndex downTo 0) {
                for (c in cakes.lastIndex downTo 0) {
                    if (c == cakes.lastIndex && g == guests.lastIndex && cakes[c].weight > guests[g].capacity) {
                        result = -1
                        break@toobig
                    }

                    if (cakes[c].count == 0) continue

                    if (guests[g].capacity >= cakes[c].weight) {
                        println("guest ${guests[g]} eating cake ${cakes[c]}")
                        cakes[c].count--
                        guests[g].cakesEaten++
                        guests[g].totalWeightEaten = guests[g].totalWeightEaten + cakes[c].weight
                        cakesEaten++
                        break
                    }
                }
            }
        }

        val totalWeightEaten = guests.sumBy { it.totalWeightEaten }
        println("guests $guests")
        println("cakes $cakes")
        println("totalCakeWeight:$totalCakeWeight totalWeightEaten:$totalWeightEaten")
        println(result)
    }
}

