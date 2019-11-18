package exercises.battlefield

/*
* https://www.hackerearth.com/practice/data-structures/arrays/1-d/practice-problems/algorithm/battlefield-13/
*/


fun main() {
    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    repeat(numberOfTestCases) {
        val battleSize = readLine()?.toInt() ?: throw Exception("error parsing battlefield size")
        val battleField = readLine()?.also {
            if (it.length != battleSize) throw Exception("battle size not equal to $battleSize")
        } ?: throw Exception("error parsing battle field")

        var numberOfMoves = Int.MAX_VALUE

        val numberOfKnights = battleField.count {
            it == 'K'
        }

//        val indexString = (0 until battleField.length).joinToString(", ")
//        println("index          : $indexString")
//        println("battleField    : ${battleField.toCharArray().joinToString()}")
//        println("numberOfKnights: $numberOfKnights")

        for (windowStart in 0 until battleSize) {
            val windowEnd = windowStart + numberOfKnights - 1

            var demonCount = 0

            for (i in windowStart..windowEnd) {
                val rollingI = if (i > battleSize - 1) i - battleSize else i

                if (battleField[rollingI] == 'D') demonCount++
            }

            if (demonCount < numberOfMoves) numberOfMoves = demonCount
        }

        println(numberOfMoves)
    }
}