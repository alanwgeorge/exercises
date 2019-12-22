package exercises.bagofgold

import java.util.*

/*
* https://www.hackerearth.com/challenges/competitive/codemonk-stacks-queues-1/algorithm/monk-and-philosophers-stone/
*/

fun main() {
    class Stack<T> {
        inner class Node<T>(val value: T, val next: Node<T>?) {
            override fun toString() = "Node(value=$value, next=$next)"
        }

        var top: Node<T>? = null

        fun push(value: T) {
            top = Node(value, top)
        }

        fun pop(): T? =
            if (top == null) {
                null
            } else {
                val value = top?.value
                top = top?.next
                value
            }

        fun size():Int {
            var node = top
            var count = 0
            while (node != null) {
                count++
                node = node.next
            }
            return count
        }

        override fun toString() = "Stack(top=$top)"
    }

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