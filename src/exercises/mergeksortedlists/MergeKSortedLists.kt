@file:Suppress("DuplicatedCode")

package exercises.mergeksortedlists

/*
* https://leetcode.com/problems/merge-k-sorted-lists/
*/

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode(`val`=$`val`, next=$next)"
    }

    fun toList(): List<Int> {
        val result = mutableListOf<Int>()
        var ptr: ListNode? = next
        result.add(`val`)

        do {
            ptr?.`val`?.let { result.add(it) }
            ptr = ptr?.next
        } while (ptr != null)

        return result
    }
}

class Solution(private val debug: Boolean = false) {
    companion object {
        var loops = 0
    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val root = ListNode(-1)
        var tail: ListNode? = root

        while (lists.filterNotNull().isNotEmpty()) {
            loops++
            var lowest: ListNode? = null
            var lowestIdx = -1
            lists.forEachIndexed { idx, node ->
                loops++
                node?.let {
                    if (debug) println("$idx $node $lowest")
                    if (it.`val` < lowest?.`val` ?: Int.MAX_VALUE) {
                        lowest = it
                        lowestIdx = idx
                    }
                }
            }
            lists[lowestIdx] = lowest?.next
            tail?.next = lowest
            tail = lowest
        }

        return root.next
    }
}


fun main() {
    val tests = listOf(
            listOf(listOf(1,4,5), listOf(1,3,4), listOf(2,6)) to listOf(1,1,2,3,4,4,5,6),
            listOf(listOf(1,2,3), listOf(5,6,7), listOf(9,10)) to listOf(1,2,3,5,6,7,9,10)
            )

    val s = Solution(debug = true)

    tests.forEach {
        println("test: $it")
        Solution.loops = 0
        val result = s.mergeKLists(it.first.toArrayListNode())
        println("${result?.toList()} loops:${Solution.loops}")
        if (result?.toList() != it.second) throw Exception("test $it failed, result ${result?.toList()}, expected ${it.second}")
    }
}

fun List<Int>.toListNode(): ListNode? {
    if (isEmpty()) return null
    val head = ListNode(first())
    var ptr = head

    for (n in this.drop(1)) {
        ListNode(n).apply {
            ptr.next = this
            ptr = this
        }
    }

    return head
}

fun List<List<Int>>.toArrayListNode(): Array<ListNode?> {
    if (this.isEmpty()) return arrayOf(null)
    val result = mutableListOf<ListNode>()

    forEach {
        it.toListNode()?.let { list ->
            result.add(list)
        }
    }

    return result.toTypedArray()
}
