package exercises.bitscount

/*
* https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/tutorial/
* */

fun main() {
    fun countBitsSet(inp: Int): Int =
            (1..Int.SIZE_BITS).fold(0)  { count, bit ->
                if (inp and (1 shl bit) > 0) {
                    count + 1
                } else {
                    count
                }
            }

    val numberOfTestCases = readLine()?.toInt() ?: throw Exception("no test cases found")

    repeat(numberOfTestCases) {
        val inNumber = readLine()?.toInt() ?: throw Exception("error parsing battlefield size")

//        println("${inNumber.toString(2)}  > ${inNumber.toString(2).count { it == '1' }}")

        println(countBitsSet(inNumber))
    }
}