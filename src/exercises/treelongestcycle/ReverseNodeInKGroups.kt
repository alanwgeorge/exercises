@file:Suppress("DuplicatedCode")

package exercises.treelongestcycle

/*
* https://leetcode.com/problems/reverse-nodes-in-k-group/
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

class Solution {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {

    }
}

data class Test(val head: List<Int>, val k: Int, val solution: List<Int>)

fun main() {
    listOf(
            Test(listOf(1,2,3,4,5), 2, listOf(2,1,4,3,5)),
            Test(listOf(1,2,3,4,5), 3, listOf(3,2,1,4,5))
    )
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