package exercises.bagofgold

import exercises.Stack
import java.util.*

/*
* https://www.hackerearth.com/challenges/competitive/codemonk-stacks-queues-1/algorithm/monk-and-philosophers-stone/
*/

fun main() {
    val coinCount = readLine()?.toInt() ?: throw Exception("error reading coin count")
    val coins = LinkedList(readLine()?.split("""\s+""".toRegex(), coinCount)?.map { it.toInt() } ?: throw Exception("error parsing coins"))
    val (commandCount, target) = readLine()?.split("""\s+""".toRegex(), 2)?.map { it.toInt() } ?: throw Exception("error parsing commandCount and target")

    var sumOfCoinsInBag = 0
    val bag = Stack<Int>()

    run loop@{
        repeat(commandCount) { commandNumber ->
            val command = readLine() ?: throw Exception("error reading command $commandNumber")
            if (command == "Harry") {
                val coin = coins.pollFirst()
                coin?.let {
                    sumOfCoinsInBag += it
                    bag.push(it)
                    if (sumOfCoinsInBag == target) return@loop
                } ?: throw Exception("ran out of coins")
            } else {
                bag.pop()?.let {
                    sumOfCoinsInBag -= it
                }
            }
        }
    }

    println("bag: $bag")
    println(bag.size())
}