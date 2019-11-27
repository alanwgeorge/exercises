package exercises.binarysearch

/*
* https://www.hackerearth.com/practice/algorithms/searching/binary-search/tutorial/
* */

fun main() {
    fun List<Int>.search(term: Int): Int {
        var start = 0
        var end = size - 1

        while (start <= end) {
            val mid = (start + end) / 2
//            println("before: $term $start $mid $end")
            when {
                get(mid) > term -> end = mid - 1
                get(mid) < term -> start = mid + 1
                else -> return mid + 1
            }
//            println("after: $term $start $mid $end")
        }

        return -1
    }

    val inputSize = readLine()?.toInt() ?: throw Exception("error parsing input size")
    val input = readLine()?.split(" ", limit = inputSize)?.map { it.toInt() }?.toMutableList() ?: throw Exception("error parsing input line")
    val numQueries = readLine()?.toInt() ?: throw Exception("error parsing number of queries")

    input.sort()

    repeat(numQueries) {
        val value = readLine()?.toInt() ?: throw Exception("error parsing input $it")
        println(input.search(value))
    }
}