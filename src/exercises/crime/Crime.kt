package exercises.crime

/*
* https://www.hackerearth.com/practice/algorithms/searching/linear-search/practice-problems/algorithm/joker-and-thieves-53e59f4a/
*/

fun main() {
    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    repeat(numberOfTestCases) {
        val (gridSize, policeReach) = readLine()?.split(" ", limit = 2)?.map { it.toInt() }?.toTypedArray()?.take(2) ?: throw Exception("error parsing gridSize and policeReach")
        val grid = mutableListOf<MutableList<Char>>()
        repeat(gridSize) {
            val gLine = readLine()?.split(" ", limit = gridSize)?.map { it.first() } ?: throw Exception("error parsing grid line $it")
            grid.add(gLine.toMutableList())
        }

//        grid.print()

        var criminalsCaught = 0

        grid.forEach { line ->
            for (i in 0 until line.size) {
                if (line[i] == 'P') {
                    val searchStart = if (i - policeReach < 0) 0 else i - policeReach
                    val searchEnd = if (i + policeReach >= line.size) line.size - 1 else i + policeReach

                    for (s in searchStart..searchEnd) {
                        if (line[s] == 'T') {
                            criminalsCaught += 1
                            line[s] = 'C'
                            break
                        }
                    }
                }
            }
        }
        println(criminalsCaught)
    }
}

fun <T: Any> List<List<T>>.print() {
    forEach {
        it.forEach {
           print("$it ")
        }
        println()
    }
}