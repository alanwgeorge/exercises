
package exercises.linearsearch

/*
* https://www.hackerearth.com/practice/algorithms/searching/linear-search/tutorial/
* */

fun main() {
    val (_, searchTerm) = readLine()?.split(" ", limit = 2)?.map { it.toLong() }?.toTypedArray()?.take(2) ?: throw Exception("error parsing size and searchTerm")
    val input = readLine()?.split(" ")?.map { it.toLong() }?.toTypedArray() ?: throw Exception("error parsing size and searchTerm")

    var lastIndex = -1

    for (i in (input.size - 1) downTo 0) {
        if (input[i] == searchTerm) {
            lastIndex = i + 1
            break
        }
    }

    println(lastIndex)
}