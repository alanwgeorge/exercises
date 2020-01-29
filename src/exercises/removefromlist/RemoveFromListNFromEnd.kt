package exercises.removefromlist

/*
* https://leetcode.com/problems/remove-nth-node-from-end-of-list/
*/

data class ListNode(val value: Int, var next: ListNode? = null)

class Solution {
    companion object {
        var loops = 0
    }
    fun removeNthFromEnd(head: ListNode, n: Int): ListNode? {
        var previousNode: ListNode? = null
        var node: ListNode? = head
        var nAheadNode: ListNode? = head
        var result: ListNode? = head

        repeat(n) {
            loops++
            nAheadNode = nAheadNode?.next
        }

        while (nAheadNode != null) {
            loops++
            previousNode = node
            node = node?.next
            nAheadNode = nAheadNode?.next
        }

        if (node == head) {
            result = head.next
        } else {
            previousNode?.next = node?.next
        }

        return result
    }
}

data class Test(val list: ListNode, val n: Int, val solution: ListNode)

fun main() {
    val tests = listOf(
            Test(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 2, ListNode(1, ListNode(2, ListNode(3,  ListNode(5))))),
            Test(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 5, ListNode(2, ListNode(3, ListNode(4,  ListNode(5))))),
            Test(ListNode(1, ListNode(2)), 1, ListNode(1))
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        Solution.loops = 0
        val result = s.removeNthFromEnd(it.list, it.n)
        println("result:$result loops:${Solution.loops}")
        if (result != it.solution) throw Exception("test $it failed, result was $result, expected ${it.solution}")
    }
}