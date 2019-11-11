package exercises.binsearch

/*
* https://www.hackerearth.com/practice/algorithms/searching/linear-search/tutorial/
* */

fun main() {
    val (_, searchTerm) = readLine()?.split(" ", limit = 2)?.map { it.toLong() }?.toTypedArray()?.take(2) ?: throw Exception("error parsing size and searchTerm")
    val input = readLine()?.split(" ")?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing size and searchTerm")

    var lastIndex = -1

    input.forEachIndexed { idx, value ->
        if (value == searchTerm) {
            lastIndex = idx + 1
        }
    }

    println(lastIndex)
}