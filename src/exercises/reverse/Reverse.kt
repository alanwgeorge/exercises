package exercises.reverse

/*
* https://www.hackerearth.com/practice/algorithms/string-algorithm/basics-of-string-manipulation/practice-problems/algorithm/terrible-chandu/
*/

fun main() {
    val inputCount = readLine()?.toInt() ?: throw Exception("error parsing input line count")
    repeat(inputCount) {
        val input = readLine()?.toCharArray() ?: throw Exception("error parsing input line")
//        println("input: ${input.toList()}")

        var s = 0
        var e = input.lastIndex

        while (e - s >= 0){
//            println("${input[s]} ${input[e]} ${e - s}")
            val temp = input[s]

            input[s] = input[e]
            input[e] = temp

            s++
            e--
        }

        println(String(input))
    }
}