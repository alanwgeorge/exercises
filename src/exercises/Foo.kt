package exercises

@ExperimentalUnsignedTypes
fun main() {
//    val foo = listOf(1,0,1,0,1,0,1,0,1)
//
//    foo.fold(0) { acc, value ->
//        val n = acc xor value
//        println(n)
//        n
//    }

    val gcd = gcd(2260, 816)

    println(gcd)

    var divisors = 0
    for (i in 1..gcd) {
       if (gcd%i == 0) divisors++
    }

    println(divisors)
}

fun isPowerOfTwo(x: Int) =
    if (x == 0) {
        false
    } else x and (x - 1) == 0

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
