package exercises.spiderqueue

import exercises.Queue

/*
* https://www.hackerearth.com/practice/data-structures/queues/basics-of-queues/practice-problems/algorithm/monk-and-chamber-of-secrets/
*/

fun main() {
    data class Spider(val power: Int, val initPosition: Int)

    val spiders = Queue<Spider>()
    val (spiderCount, numberToSelect) = readLine()?.split("""\s+""".toRegex(), 2)?.map { it.toInt() } ?: throw Exception("error parsing spider count and number to select")
    readLine()?.split("""\s+""".toRegex(), spiderCount)?.mapIndexed { pos, power -> Spider(power.toInt(), pos + 1) }?.forEach { spiders.enqueue(it) } ?: throw Exception("error parsing spiders")

    val powerfulSpiders = Queue<Spider>()

    repeat(numberToSelect) { _ ->
        val holdingQueue = Queue<Spider>()
        var strongest: Spider? = null
        repeat(numberToSelect) { _ ->
            spiders.dequeue()?.let { spider ->
                strongest = strongest?.let {
                    if (spider.power > it.power) {
                        spider
                    } else {
                        strongest
                    }
                } ?: spider

                holdingQueue.enqueue(spider)
            }
        }

        var heldSpider = holdingQueue.dequeue()
        while (heldSpider != null) {
            if (heldSpider.initPosition == strongest?.initPosition) {
                powerfulSpiders.enqueue(heldSpider)
            } else {
                val power = if (heldSpider.power > 0) heldSpider.power - 1 else 0
                spiders.enqueue(heldSpider.copy(power = power))
            }
            heldSpider = holdingQueue.dequeue()
        }
    }

    val result = powerfulSpiders.toList().map { it.initPosition }.joinToString(" ")
    println(result)
}