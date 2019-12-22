package exercises.breakupapp

/*
* https://www.hackerearth.com/practice/algorithms/searching/linear-search/practice-problems/algorithm/breakup-app/
* */

fun main() {
    val inputCount = readLine()?.toInt() ?: throw Exception("error parsing input line count")
    val input = mutableListOf<List<String>>()

    repeat(inputCount) {
        input.add(readLine()?.split("""\s+""".toRegex()) ?: emptyList())
    }

    val accum = Array(31) { 0 }
    val weights = mapOf("G:" to 2, "M:" to 1)

    input.map {
        it.first() to it.drop(1).map {
            it.toIntOrNull()
        }.filterNotNull()
    }.filter {
        it.second.isNotEmpty()
    }.groupBy {
        it.first
    }.map {
       it.key to it.value.flatMap {
           it.second
       }
    }.filter {
        it.first == "G:" || it.first == "M:"
    }.forEach {
//        println(it)
        val weight = weights.getOrDefault(it.first, 0)

        if (weight > 0) {
            it.second.forEach {
                accum[it - 1] += weight
            }
        }
    }

//    accum.forEachIndexed { index, i ->
//        println("${index + 1}, $i")
//    }

    var selectedDay = 0

    for (day in accum.indices) {
        if (accum[day] > accum[selectedDay]) {
            selectedDay = day
        }
    }

    if (selectedDay + 1 == 19 || selectedDay + 1 == 20) {
        println("Date")
    } else {
        println("No Date")
    }
}