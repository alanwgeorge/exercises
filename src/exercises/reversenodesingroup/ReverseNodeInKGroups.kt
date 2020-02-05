@file:Suppress("DuplicatedCode", "unused")

package exercises.reversenodesingroup

import java.util.*

/*
* https://leetcode.com/problems/reverse-nodes-in-k-group/
*/

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode(`val`=$`val`, next=$next)"
    }
}

class Solution(private val debug: Boolean = false) {
    var loops = 0

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        val root = ListNode(-1)
        val stack = LinkedList<ListNode>()
        stack.addFirst(root)
        var curNode: ListNode? = head
        var curNodeIdx = 1

        while (curNode != null) {
            loops++
            stack.addLast(curNode)
            if (curNodeIdx % k == 0) {
                curNode = curNode.next
                curNodeIdx++
                var base = stack.pollFirst()
                while (stack.peekFirst() != null) {
                    loops++
                    val next = stack.pollLast()
                    base.next = next
                    base = next
                }
                base.next = null
                stack.addFirst(base)
                if (debug) println("$curNodeIdx ${root.next?.toList()}")
            } else {
                curNode = curNode.next
                curNodeIdx++
            }
        }

        if (stack.size > 1) {
            var base = stack.pollFirst()
            while (stack.peekFirst() != null) {
                loops++
                val next = stack.pollFirst()
                base.next = next
                base = next
            }
        }

        return root.next
    }

    fun reverseKPairs(head: ListNode?, k: Int): ListNode? {
        val root = ListNode(-1)
        var first: ListNode? = head
        var firstParent: ListNode? = root
        var last: ListNode? = head
        var lastParent: ListNode? = root
        var currentNodeIdx = 1

        while (last != null) {
            loops++
            if (currentNodeIdx % k == 0) {
                if (debug) println("before: $currentNodeIdx ${firstParent?.`val`} ${first?.`val`} ${lastParent?.`val`} ${last.`val`} ${root.next?.toList()}")
                firstParent?.next = last
                lastParent?.next = first
                val tmp = first?.next
                first?.next = last.next
                last.next = tmp

                firstParent = first
                first = firstParent?.next

                lastParent = firstParent
                last = first
                if (debug) println("after: $currentNodeIdx ${firstParent?.`val`} ${first?.`val`} ${lastParent?.`val`} ${last?.`val`} ${root.next?.toList()}")
            } else {
                lastParent = last
                last = last.next
            }
            currentNodeIdx++
        }

        return if (k >= currentNodeIdx) head else root.next
    }
}

data class Test(val head: List<Int>, val k: Int, val solution: List<Int>)

fun main() {
    val tests = listOf(
            Test(listOf(1, 2, 3, 4, 5), 2, listOf(2, 1, 4, 3, 5)),
            Test(listOf(1, 2, 3, 4, 5), 3, listOf(3, 2, 1, 4, 5)),
            Test(listOf(1), 2, listOf(1)),
            Test(listOf(1,2,3,4), 4, listOf(4,3,2,1))
    )

    val s = Solution(debug = true)

    tests.forEach {
        println("test: $it")
        s.loops = 0
        val result = s.reverseKGroup(it.head.toListNode(), it.k)?.toList() ?: emptyList()
        println("result:$result loops:${s.loops}")
        if (result != it.solution) throw Exception("test $it failed, result $result, expected ${it.solution}")
    }
}

fun ListNode.toList(): List<Int> {
    val result = mutableListOf<Int>()
    var ptr: ListNode? = next
    result.add(`val`)

    do {
        ptr?.`val`?.let { result.add(it) }
        ptr = ptr?.next
    } while (ptr != null)

    return result
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