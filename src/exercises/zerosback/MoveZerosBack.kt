package exercises.zerosback

class Solution {
    fun moveZerosBack(input: IntArray): IntArray {
        var zPtr = 0
        var nzPtr: Int

        while (true) {
            while (input[zPtr] != 0 && zPtr < input.lastIndex) zPtr++
            if (zPtr >= input.lastIndex) break
            nzPtr = zPtr + 1

            while (input[nzPtr] == 0 && nzPtr < input.lastIndex) nzPtr++
            input[zPtr] = input[nzPtr]
            input[nzPtr] = 0

            if (nzPtr == input.lastIndex) break
        }

        return input
    }
}

fun main() {
    val tests = listOf(
        listOf(1,5,0,9,0,8,7,0) to listOf(1,5,9,8,7,0,0,0),
        listOf(0,0,0,0,0,0,0,0) to listOf(0,0,0,0,0,0,0,0),
        listOf(1,2,3,4,0,0,0,0) to listOf(1,2,3,4,0,0,0,0),
        listOf(0,0,0,0,1,2,3,4) to listOf(1,2,3,4,0,0,0,0),
        listOf(1,2,3,4,5,6,7,8) to listOf(1,2,3,4,5,6,7,8)
    )

    val s = Solution()

    tests.forEach { (input, expected) ->
        println("test:$input -> $expected")
        val result = s.moveZerosBack(input.toIntArray()).toList()
        println("result:$result")
        if (result != expected) throw Exception("test $input failed, result $result. expected $expected")
    }
}