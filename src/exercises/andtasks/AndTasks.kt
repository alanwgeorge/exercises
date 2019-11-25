package exercises.andtasks

/*
* https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/and-operation-3-0b1a025c/
* */

fun main() {
    val (inputSize, numberOfTasks) = readLine()?.split(" ", limit = 2)?.map { it.toInt() }?.toTypedArray()?.take(2) ?: throw Exception("error parsing gridSize and policeReach")
    val input = readLine()?.split(" ", limit = inputSize)?.map { it.toInt() }?.toMutableList() ?: throw Exception("error parsing input line")

//    println("original input: $input")

    repeat(numberOfTasks) {
        val (left, right, value) = readLine()?.split(" ", limit = 3)?.map { it.toInt() } ?: throw Exception("error parsing task $it")

        for (i in (left - 1) until right) {
            input[i] = input[i] and value
        }

//        println("task $left, $right, $value result $input")
    }

    println(input.joinToString(" "))
}

fun main2() {  // abandoned attempt
    var original = mutableListOf(7,7,7,7,7)

    val commands = listOf(
            listOf(1,3,4),
            listOf(1,5,6)
    )

    val masks = MutableList(original.size) { Int.MAX_VALUE }

    for (i in 0 until commands.size) {
        val (l,r,v) = commands[i]
        for (range in (l - 1) until r) masks[range] = masks[range] and v
        println("${v.toString(2)} $masks")
    }

    for (i in 0 until original.size) {
        original[i] = original[i] and masks[i]
    }

    println("original = $original")
}