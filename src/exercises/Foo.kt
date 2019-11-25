package exercises

@ExperimentalUnsignedTypes
fun main() {
    val foo = listOf(1,1,1,1,0,1,1,1,1)

    val bar = foo.fold(1) { acc, value ->
        val n = acc and value
        println(n)
        n
    }

    println(bar)
}

fun isPowerOfTwo(x: Int) =
    if (x == 0) {
        false
    } else x and (x - 1) == 0

fun <T> printAllSubSets(inp: List<T>) {
    val numOfSubs = 1 shl inp.size

    for (i in 0 until numOfSubs) {
        print("${i.toString(2)} = {")

        for(j in 0 until inp.size) {
            if (i and (1 shl j) > 0) {
                print("'${inp[j]}' ")
            }
        }
        println("}")
    }
}

@ExperimentalUnsignedTypes
fun largestPower(inp: UInt): UInt {

    var tmp = inp

    tmp = tmp or (tmp shr 1)
    tmp = tmp or (tmp shr 2)
    tmp = tmp or (tmp shr 4)
    tmp = tmp or (tmp shr 8)

    val result = (tmp + 1u) shr 1

    return result
}

@ExperimentalUnsignedTypes
fun countBitsSet(inp: UInt): Int =
        (1..UInt.SIZE_BITS).fold(0)  { count, bit ->
            if (inp and (1u shl bit) > 0u) {
                count + 1
            } else {
                count
            }
        }
