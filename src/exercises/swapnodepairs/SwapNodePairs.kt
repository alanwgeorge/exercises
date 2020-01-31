@file:Suppress("DuplicatedCode")

package exercises.swapnodepairs

/*
* https://leetcode.com/problems/swap-nodes-in-pairs/
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
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        val newHead = head.next
        var ptr1: ListNode? = null
        var ptr2: ListNode? = head
        var ptr3: ListNode? = head.next
        var ptr4: ListNode? = head.next?.next

        do {
            if (debug) println("before: 1:$ptr1 2:$ptr2 3:$ptr3 4:$ptr4")
            ptr1?.next = ptr3
            ptr2?.next = ptr4
            ptr3?.next = ptr2

            ptr1 = ptr2
            ptr2 = ptr1?.next
            ptr3 = ptr1?.next?.next
            ptr4 = ptr1?.next?.next?.next

            if (debug) println("after: 1:$ptr1 2:$ptr2 3:$ptr3 4:$ptr4")
        }
        while (ptr3 != null)

        return newHead
    }
}

fun main() {
    val tests = listOf(
            listOf(1, 2, 3, 4) to listOf(2, 1, 4, 3),
            listOf(1, 2, 3, 4, 5) to listOf(2, 1, 4, 3, 5)
    )

    val s = Solution(debug = true)

    tests.forEach {
        println("test: $it")
        val result = s.swapPairs(it.first.toListNode())
        println(result?.toList())
        if (result?.toList() != it.second) throw Exception("rest $it failed, result ${result?.toList()}, expected ${it.second}")
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