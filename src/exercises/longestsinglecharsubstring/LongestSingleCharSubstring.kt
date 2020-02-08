package exercises.longestsinglecharsubstring

/*
* https://www.youtube.com/watch?v=qRNB8CV3_LU
*/

fun main() {
    val tests = listOf(
            "aabcddbbbea" to ('b' to 3),
            "abcdefg" to ('a' to 1),
            "aaaaaaaaaa" to ('a' to 10)
    )

    tests.forEach {
        println("test: $it")
        val result = it.first.findLongestSingleCharSubstring()
        println(result)
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}

fun CharSequence.findLongestSingleCharSubstring(): Pair<Char, Int> {
    if (isNullOrEmpty()) throw Exception("string null or empty")
    var count = 1
    var highestCount = 1
    var highestChar = first()
    var curChar = first()

    for (c in drop(1)) {
        if (c == curChar) {
            count++
            if (count > highestCount) {
                highestCount = count
                highestChar = curChar
            }
        } else {
            count = 1
            curChar = c
        }
    }

    return highestChar to highestCount
}