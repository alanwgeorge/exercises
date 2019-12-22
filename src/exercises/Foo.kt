package exercises

@ExperimentalUnsignedTypes
fun main() {
    run run@{
        listOf(1, 2, 3, 4, 5, 6, 7).forEach escape@{
            if (it == 3) return@run
            println("$it")
        }
    }

    println("the end")
}

@Suppress("unused")
fun isPowerOfTwo(x: Int) =
    if (x == 0) {
        false
    } else x and (x - 1) == 0

@Suppress("unused")
fun <T> printAllSubSets(inp: List<T>) {
    val numOfSubs = 1 shl inp.size

    for (i in 0 until numOfSubs) {
        print("${i.toString(2)} = {")

        for(j in inp.indices) {
            if (i and (1 shl j) > 0) {
                print("'${inp[j]}' ")
            }
        }
        println("}")
    }
}

fun gcd(x: Int, y: Int): Int = (if (x == 0) y else gcd(y%x, x)).also { println("x:$x y:$y") }

@Suppress("unused")
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

@Suppress("unused")
@ExperimentalUnsignedTypes
fun countBitsSet(inp: UInt): Int =
        (1..UInt.SIZE_BITS).fold(0)  { count, bit ->
            if (inp and (1u shl bit) > 0u) {
                count + 1
            } else {
                count
            }
        }
