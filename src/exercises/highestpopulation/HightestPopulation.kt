@file:Suppress("unused")

package exercises.highestpopulation

/*
* https://www.youtube.com/watch?v=4UWDyJq8jZg
*/

class Solution {
    var loops = 0

    fun highestPopulationYear(lifeSpans: List<Pair<Int, Int>>): Int {
        val counter = sortedMapOf<Int, Int>()
        var highestCount = -1
        var result = -1

        lifeSpans.forEach { lifeSpan ->
            for (y in lifeSpan.first..lifeSpan.second) {
                loops++
                val yearCount = counter.getOrPut(y) {
                    0
                }

                counter[y] = yearCount + 1

                counter[y]?.let {
                    if (highestCount < it) {
                        highestCount = it
                        result = y
                    }
                }
            }
        }

        println(counter)

        return result
    }
}

class Solution2 {
    var loops = 0

    fun highestPopulationYear(lifeSpans: List<Pair<Int, Int>>): Int {
        val yearsOfChange = sortedMapOf<Int, Int>()

        lifeSpans.forEach { lifeSpan ->
            loops++
            val bCount = yearsOfChange.getOrDefault(lifeSpan.first, 0)
            yearsOfChange[lifeSpan.first] = bCount + 1
            val dCount = yearsOfChange.getOrDefault(lifeSpan.second + 1, 0)
            yearsOfChange[lifeSpan.second + 1] = dCount - 1
        }

        var runningPopulation = 0
        var maxPopulation = -1
        var maxYear = -1
        for ((year, change) in yearsOfChange) {
            loops++
            if ((runningPopulation + change) > maxPopulation) {
                maxPopulation = runningPopulation + change
                maxYear = year
            }
            runningPopulation += change
        }

        println(yearsOfChange)

        return maxYear
    }
}

fun main() {
    val tests = listOf(
            listOf(1 to 10, 10 to 20) to 10,
            listOf(1 to 10, 9 to 20) to 9,
            listOf(2000 to 2010, 1975 to 2005, 1975 to 2003, 1803 to 1809, 1750 to 1869, 1840 to 1935, 1803 to 1921, 1894 to 1921) to 1803
    )

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        s.loops = 0
        val result = s.highestPopulationYear(it.first)
        println("result:$result loops:${s.loops}")
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}